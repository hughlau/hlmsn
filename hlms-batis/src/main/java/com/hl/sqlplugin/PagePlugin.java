package com.hl.sqlplugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.log4j.Logger;

import com.hl.annotation.TableSeg;
import com.hl.sqlplugin.PagePlugin;
import com.hl.sqlplugin.PageView;
import com.hl.sqlplugin.ReflectHelper;
import com.hl.sqlplugin.SQLServer2008Dialect;
import com.hl.sqlutil.BaseEntityMap;
import com.hl.sqlutil.Common;
import com.hl.sqlutil.EhcacheUtils;


/**
 * Mybatis的分页查询插件，通过拦截StatementHandler的prepare方法来实现。 
 * 只有在参数列表中包括Page类型的参数时才进行分页查询。 
 * 在多参数的情况下，只对第一个Page类型的参数生效。 
 * 另外，在参数列表中，Page类型的参数无需用@Param来标注 
 * @author lanyuan
 * 2015-03-20
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@SuppressWarnings("unchecked")
@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class })})
public class PagePlugin implements Interceptor {
	public final static Logger logger = Logger.getLogger(PagePlugin.class);
	private static String dialect = null;//数据库类型
	private static String pageSqlId = ""; // mybaits的数据库xml映射文件中需要拦截的ID(正则匹配)
	@SuppressWarnings("rawtypes")
	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper
					.getValueByFieldName(delegate, "mappedStatement");
			/**
			 * 方法1：通过ＩＤ来区分是否需要分页．.*query.*
			 * 方法2：传入的参数是否有page参数，如果有，则分页，
			 */
		//	if (mappedStatement.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if (parameterObject == null) {
					//throw new NullPointerException("boundSql.getParameterObject() is null!");
					return ivk.proceed();
				} else {
					if(mappedStatement.getId().indexOf(".BaseMapper.")>-1){
						Connection connection = (Connection) ivk.getArgs()[0];
						//parameterObject = toHashMap(model, pageView);
						 // 公共方法被调用
						Map formMap = null;
						if(parameterObject instanceof BaseEntityMap){
							formMap = toHashMap(parameterObject);
						}else if(parameterObject instanceof Map){
							Map map = (Map) parameterObject;
							if(map.containsKey("list")){
								List<Object> lists = (List<Object>) map.get("list");
								String sql = Plugin.joinSql(connection, mappedStatement, boundSql, formMap,lists);
								ReflectHelper.setValueByFieldName(boundSql, "sql", sql);
								return ivk.proceed();
							}else{
								Class fm = (Class) map.get("param3");
								Object o = fm.newInstance();
								formMap = toHashMap(o);
								formMap.put("key", map.get("param1"));
								formMap.put("value", map.get("param2"));
							}
						}else{
							throw new NullPointerException("调用公共方法，传入参数有错误！具体请看参数说明！");
						}
						String sql = Plugin.joinSql(connection, mappedStatement, boundSql, formMap,null);
						ReflectHelper.setValueByFieldName(boundSql, "sql", sql);
						return ivk.proceed();
					}
					PageView pageView = null;
					if (parameterObject instanceof PageView) { // 参数就是Pages实体
						pageView = (PageView) parameterObject;
					} else if (parameterObject instanceof Map) {
						for (Entry entry : (Set<Entry>) ((Map) parameterObject)
								.entrySet()) {
							if (entry.getValue() instanceof PageView) {
								pageView = (PageView) entry.getValue();
								break;
							}
						}
					} else { // 参数为某个实体，该实体拥有Pages属性
						pageView = ReflectHelper.getValueByFieldType(
								parameterObject, PageView.class);
						if (pageView == null) {
							return ivk.proceed();
						}
					}
					if (pageView == null) {
						return ivk.proceed();
					}
					String sql = boundSql.getSql();
					Connection connection = (Connection) ivk.getArgs()[0];
					setPageParameter(sql, connection, mappedStatement, boundSql, parameterObject, pageView);
					String pageSql = generatePagesSql(sql, pageView);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
				}
			//}
		}
		return ivk.proceed();
	}
	/**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
     * <code>PageParameter</code>获得相关信息。
     * 
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
	 * @throws SQLException 
     */
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
            BoundSql boundSql,Object parameterObject, PageView pageView) throws SQLException {
        // 记录总记录数
    	PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			String countSql = "";
			try {
				 countSql = "select count(1) from " + suffixStr(removeOrderBys(sql));
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			} catch (Exception e) {
				PagePlugin.logger.error(countSql+" 统计Sql出错,自动转换为普通统计Sql语句!");
				countSql = "select count(1) from (" + sql+ ") tmp_count"; 
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			}
			int count = 0;
			if (rs.next()) {
				count = ((Number) rs.getObject(1)).intValue();
			}
			pageView.setRowCount(count);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				countStmt.close();
			} catch (Exception e) {
			}
		}

    }
	/**
	   *   select
	     *  id,
		 * 	articleNo,
		 * sum(ddd) ss,
		 * 	articleName,
	     *  (SELECT loginName from ly_userinfo u where u.id=userId) loginName,
		 * 	(SELECT userName from ly_userinfo u where u.id=userId) userName,
		 * sum(ddd) ss
		 * from article	
		 * 兼容以上子查询
		 * //去除sql ..from 前面的字符。考虑 aafrom fromdd 等等情况
	   */
	public static String suffixStr(String toSql){
		toSql=toSql.toUpperCase();
		int sun = toSql.indexOf("from");
		String f1 = toSql.substring(sun-1,sun);
		String f2 = toSql.substring(sun+4,sun+5);
		if(f1.trim().isEmpty()&&f2.trim().isEmpty()){//判断第一个from的前后是否为空
			String s1 = toSql.substring(0,sun);
			int s0 =s1.indexOf("(");
			if(s0>-1){
				int se1 =s1.indexOf("select");
				if(s0<se1){
					if(se1>-1){
						String ss1 = s1.substring(se1-1,se1);
						String ss2 = s1.substring(se1+6,se1+7);
						if(ss1.trim().isEmpty()&&ss2.trim().isEmpty()){//判断第一个from的前后是否为空
							return suffixStr(toSql.substring(sun+5));
						}
					}
				}	
				int se2 =s1.indexOf("(select");
					if(se2>-1){
						String ss2 = s1.substring(se2+7,se2+8);
						if(ss2.trim().isEmpty()){//判断第一个from的前后是否为空
							return suffixStr(toSql.substring(sun+5));
						}
					}
					if(se1==-1&&se2==-1){
						return toSql.substring(sun+5);
					}else{
						toSql=toSql.substring(sun+5);
					}
			}else{
				toSql=toSql.substring(sun+5);
			}
		}
		return toSql;
	}
	public static void main(String[] args) {
		String sql="  select "+
		 "	articleNo "+
		 " from article left jion aefv where 1=(SELECT userName from ly_userinfo u where u.id=userId) "
		 + "and id = sdf   order by as asc";
		sql=removeOrderBys(sql);
		System.out.println(sql);
		System.out.println(suffixStr(sql));
	}
	 /** 
   * 去除Sql的orderBy。 
   * @param toSql 
   * @return String
   *
   */  
  private static String removeOrderBys(String toSql) {  
	  	toSql=toSql.toUpperCase();
	  	int sun = toSql.indexOf("order");
	  	if(sun>-1){
	  	  	String f1 = toSql.substring(sun-1,sun);
	  		String f2 = toSql.substring(sun+5,sun+5);
	  		if(f1.trim().isEmpty()&&f2.trim().isEmpty()){//判断第一个from的前后是否为空
	  		  	String zb = toSql.substring(sun);
	  		  	int s0 =zb.indexOf(")");
	  		  	if(s0>-1){//from之前是否有括号
	  		  		String s1=toSql.substring(0,sun);
	  		  		String s2 =zb.substring(s0);
	  		  		return removeOrderBys(s1+s2);
	  		  	}else{
	  		  		toSql=toSql.substring(0,sun);
	  		  	}
	  		}
	  	}
		return toSql;
  }
	
    /**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	public static String generatePagesSql(String sql, PageView page) {
		if (page != null) {
			if("mysql".equals(dialect)){
				return buildPageSqlForMysql(sql, page).toString();
			}else if("oracle".equals(dialect)){
				return buildPageSqlForOracle(sql, page).toString();
			}else if("SQLServer2008".equals(dialect)){
				return buildPageSqlForSQLServer2008Dialect(sql, page).toString();
			}
		}
		return sql;
	}
	  /**
     * mysql的分页语句
     * 
     * @param sql
     * @param page
     * @return String
     */
    public static StringBuilder buildPageSqlForMysql(String sql, PageView page) {
    	 StringBuilder pageSql = new StringBuilder(100);
         String beginrow = String.valueOf((page.getPageNow() - 1) * page.getPageSize());
         pageSql.append(sql);
         pageSql.append(" limit " + beginrow + "," + page.getPageSize());
         return pageSql;
    }

    /**
     * 参考hibernate的实现完成oracle的分页
     * 
     * @param sql
     * @param page
     * @return String
     */
    public static StringBuilder buildPageSqlForOracle(String sql, PageView page) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((page.getPageNow()) * page.getPageSize());
        String endrow = String.valueOf(page.getPageNow()+1 * page.getPageSize());

        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(endrow);
        pageSql.append(") where row_id > ").append(beginrow);
        return pageSql;
    }
    /**
     * 参考hibernate的实现完成SQLServer2008的分页
     * 
     * @param sql
     * @param page
     * @return String
     */
    public static String buildPageSqlForSQLServer2008Dialect(String sql, PageView page) {
        SQLServer2008Dialect dialect = new SQLServer2008Dialect(); 
        String sqlbuild = dialect.getLimitString(sql, (page.getPageNow() - 1) * page.getPageSize(), page.getPageSize());
        return sqlbuild;
    }
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@SuppressWarnings("restriction")
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (isEmpty(dialect)) {
			try {
				throw new PropertyException("dialectName or dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		} 
		pageSqlId = p.getProperty("pageSqlId");//根据id来区分是否需要分页
		if (isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Map<String, Object> toHashMap(Object parameterObject) {
		Map<String, Object> froMmap = (BaseEntityMap<String, Object>) parameterObject;
		try {
			String name = parameterObject.getClass().getName();
			Class<?> clazz = Class.forName(name);  
			boolean flag = clazz.isAnnotationPresent(TableSeg.class);  //某个类是不是存在TableSeg注解
			if (flag) {  
				TableSeg table = (TableSeg) clazz.getAnnotation(TableSeg.class);
				logger.info(" 公共方法被调用,传入参数 ==>> " + froMmap);
				froMmap.put("db_table", table.tableName());
			}else{
				throw new NullPointerException("在"+name+" 没有找到数据库表对应该的注解!");
			}
			return froMmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return froMmap;
	}
	
	@SuppressWarnings("unchecked")
	public static final String joinSql(Connection connection, MappedStatement mappedStatement,
			BoundSql boundSql, Map<String, Object> formMap, List<Object> formMaps) throws Exception {
		Object table = null;
		String sql = "";
		Map<String, Object> mapfield=null;
		String field = null;
		if (null != formMap) {
			table = formMap.get("db_table");
			mapfield=(Map<String, Object>) EhcacheUtils.get(table);
			field = mapfield.get("field").toString();
			sql = " select "+field+" from " + String.valueOf(table);
		}
		String sqlId = mappedStatement.getId();
		sqlId = sqlId.substring(sqlId.lastIndexOf(".") + 1);
		if (Configuration.FINDBYWHERE.equals(sqlId)) {
			if (null != formMap.get("where")
					&& !StringUtils.isBlank(formMap.get("where").toString())) {
				sql += " " + formMap.get("where").toString();
			}
		} else if (Configuration.FINDBYPAGE.equals(sqlId)) {
			String[] fe = field.split(",");
			String param = "";
			for (String string : fe) {
				if (formMap.containsKey(string)) {
					Object v = formMap.get(string);
					if (v.toString().indexOf("%") > -1) {
						param += " and " + string + " like '" + v + "'";
					} else {
						param += " and " + string + " = '" + v + "'";
					}
				}
			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += " where " + param;
			}
			Object by = formMap.get("$orderby");
			if (null != by) {
				sql += " " + by;
			}
			Object paging = formMap.get("paging");
			if (null == paging) {
				throw new Exception(
						"调用findByPage接口,必须传入PageView对象! formMap.set(\"paging\", pageView);");
			} else if (StringUtils.isBlank(paging.toString())) {
				throw new Exception(
						"调用findByPage接口,必须传入PageView对象! formMap.set(\"paging\", pageView);");
			}
			PageView pageView = (PageView) paging;
			setCount(sql, connection, boundSql, pageView);
			sql = PagePlugin.generatePagesSql(sql, pageView);
		} else if (Configuration.DELETEBYNAMES.equals(sqlId)) {
			sql = "delete from " + table.toString() + " where ";
			String param = "";
			for (Entry<String, Object> entry : formMap.entrySet()) {
				if (!"db_table".equals(entry.getKey()) && null != entry.getValue()
						&& !"_t".equals(entry.getKey()))
					param += " and " + entry.getKey() + " in (" + entry.getValue() + ")";
			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += param;
			}
		} else if (Configuration.DELETEBYATTRIBUTE.equals(sqlId)) {
			sql = "delete from " + table.toString() + " where " + formMap.get("key");
			if (null != formMap.get("value")) {
				sql += " in (" + formMap.get("value") + ")";
			}
		} else if (Configuration.FINDBYNAMES.equals(sqlId)) {
			String[] fe = field.split(",");
			String param = "";
			for (String string : fe) {
				if (formMap.containsKey(string)) {
					Object v = formMap.get(string);
					if (v.toString().indexOf("%") > -1)// 处理模糊查询
					{
						param += " and " + string + " like '" + v + "'";
					} else {
						param += " and " + string + " = '" + v + "'";
					}
				}
			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += " where " + param;

			}
			Object by = formMap.get("$orderby");
			if (null != by) {
				sql += " " + by;
			}
		} else if (Configuration.FINDBYATTRIBUTE.equals(sqlId)) {
			sql = "select * from " + table.toString() + " where " + formMap.get("key");
			if (null != formMap.get("value") && formMap.get("value").toString().indexOf("%") > -1)// 处理模糊查询
			{
				sql += " LIKE '" + formMap.get("value") + "'";
			} else {
				Object v = formMap.get("value");
				sql += " in ('" + v + "')";

			}
		} else if (Configuration.ADDENTITY.equals(sqlId)) {
			String[] fe = field.split(",");
			String fieldString = "";
			String fieldValues = "";
			for (String string : fe) {
				Object v = formMap.get(string);
				if (null != v && !StringUtils.isBlank(v.toString())) {
					fieldString += string + ",";
					fieldValues += "'" + v + "',";
				}
			}
			sql = "insert into " + table.toString() + " (" + ResolverUtil.trimComma(fieldString)
					+ ")  values (" + ResolverUtil.trimComma(fieldValues) + ")";
		} else if (Configuration.EDITENTITY.equals(sqlId)) {
			String[] fe = field.split(",");
			String fieldString = "";
			String where = "";
			for (String string : fe) {
				Object v = formMap.get(string);
				if (null != v && !StringUtils.isBlank(v.toString())) {
					String key = mapfield.get(Configuration.COLUMN_KEY).toString();
					if (!StringUtils.isBlank(key)) {
						if (key.equals(string)) {
							where = "WHERE " + key + " = '" + v + "'";
						} else {
							fieldString += string + "='" + v + "',";
						}
					} else {
						throw new NullPointerException("update操作没有找到主键!");
					}

				}
			}

			sql = "update " + table.toString() + " set " + ResolverUtil.trimComma(fieldString)
					+ " " + where;
		} else if (Configuration.FINDBYFRIST.equals(sqlId)) {
			sql = "select * from " + table.toString() + " where " + formMap.get("key");
			if(null!=formMap.get("value")&&!"".equals(formMap.get("value").toString()))
			{
				sql += " = '"+formMap.get("value")+"'";
			}else{
				throw new Exception(sqlId+" 调用公共方法异常!,传入参数错误！");
			}
			

		} else if (Configuration.BATCHSAVE.equals(sqlId)) {
			if (null != formMaps && formMaps.size() > 0) {
				table = Common.toHashMap(formMaps.get(0)).get(Configuration.DB_TABLE);
				mapfield = (Map<String, Object>) EhcacheUtils.get(table);
				field = mapfield.get(Configuration.FIELD).toString();
			}
			sql = "insert into " + table.toString();
			String fieldString = "";
			String fs = "";
			String fd = "";
			String fieldValues = "";
			String fvs = "";
			for (int i = 0; i < formMaps.size(); i++) {
				Object object = formMaps.get(i);
				@SuppressWarnings("unchecked")
				Map<String, Object> froMmap = (Map<String, Object>) object;
				String[] fe = field.split(",");
				for (String string : fe) {
					Object v = froMmap.get(string);
					if (null != v && !StringUtils.isBlank(v.toString())) {
						fieldString += string + ",";
						fieldValues += "'" + v + "',";
					}
				}
				if (i == 0) {
					fd = fieldString;
				}
				fvs += "(" + ResolverUtil.trimComma(fieldValues) + "),";
				fs += " insert into " + table.toString() + " ("
						+ ResolverUtil.trimComma(fieldString) + ")  values ("
						+ ResolverUtil.trimComma(fieldValues) + ");";
				fieldValues = "";
				fieldString = "";
			}
			String v = ResolverUtil.trimComma(fvs);
			sql = "insert into " + table.toString() + " (" + ResolverUtil.trimComma(fd)
					+ ")  values " + ResolverUtil.trimComma(fvs) + "";
			String[] vs = v.split("\\),");
			boolean b = false;
			for (String string : vs) {
				if (string.split(",").length != fd.split(",").length) {
					b = true;
				}
			}
			if (b) {
				sql = fs.substring(0, fs.length() - 1);
			}

		} else {
			throw new Exception("调用公共方法异常!");
		}
		return sql;
	}
	
	public static void setCount(String sql, Connection connection, BoundSql boundSql,
			PageView pageView) throws SQLException {
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			String countSql = "";
			try {
				 countSql = "select count(1) from " + suffixStr(removeOrderBys(sql));
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			} catch (Exception e) {
				PagePlugin.logger.error(countSql+" 统计Sql出错,自动转换为普通统计Sql语句!");
				countSql = "select count(1) from (" + sql+ ") tmp_count"; 
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			}
			int count = 0;
			if (rs.next()) {
				count = ((Number) rs.getObject(1)).intValue();
			}
			pageView.setRowCount(count);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				countStmt.close();
			} catch (Exception e) {
			}
		}

	}
}
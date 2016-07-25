package com.hl.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hl.sqlutil.BaseEntityMap;
import com.hl.sqlplugin.PageView;

public class BaseController {

	 public PageView pageView = null;
	    public PageView getPageView(String pageNow,String pageSize) {
	        if (null == pageNow || "".equals(pageNow) || "".equals(pageNow.trim()) || "null".equalsIgnoreCase(pageNow)) {
	            pageView = new PageView(1);
	        } else {
	            pageView = new PageView(Integer.parseInt(pageNow));
	        }
	        if (null == pageSize || "".equals(pageSize) || "".equals(pageSize.trim()) || "null".equalsIgnoreCase(pageSize)) {
	            pageSize = "10";
	        } 
	        pageView.setPageSize(Integer.parseInt(pageSize));
	        return pageView;
	    }
	    
	    public <T> T toFormMap(T t,String pageNow,String pageSize){
	        @SuppressWarnings("unchecked")
	        BaseEntityMap<String, Object> formMap = (BaseEntityMap<String, Object>) t;
	        formMap.put("paging", getPageView(pageNow, pageSize));
	        return t;
	    }
	    

	    /**
	     * 获取页面传递的某一个参数值,
	     */
	    public String getPara(String key){
	        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	        return request.getParameter(key);
	    }
	    
	    /**
	     * 获取页面传递的某一个数组值,
	     */
	    public String[] getParaValues(String key){
	        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	        return request.getParameterValues(key);
	    }
	   
	    
	    /**
	     * 获取传递的所有参数,
	     * 反射实例化对象，再设置属性值
	     * 通过泛型回传对象.
	     */
	    public <T> T getFormMap(Class<T> clazz){
	        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	        Enumeration<String> en = request.getParameterNames();
	        T t = null;
	        try {
	            t = clazz.newInstance();
	            @SuppressWarnings("unchecked")
	            BaseEntityMap<String, Object> map = (BaseEntityMap<String, Object>) t;
	            while (en.hasMoreElements()) {
	                String nms = en.nextElement().toString();
	                if(nms.endsWith("[]")){
	                    String[] as = request.getParameterValues(nms);
	                    if(as!=null&&as.length!=0&&as.toString()!="[]"){
	                        String mname = t.getClass().getSimpleName().toUpperCase();
	                        if(nms.toUpperCase().startsWith(mname)){
	                            nms=nms.substring(nms.toUpperCase().indexOf(mname)+1);
	                            map.put( nms,as);
	                        }
	                    }
	                }else{
	                    String as = request.getParameter(nms);
	                    if(!(null == as || "".equals(as) || "".equals(as.trim()) || "null".equalsIgnoreCase(as))){
	                        String mname = t.getClass().getSimpleName().toUpperCase();
	                        if(nms.toUpperCase().startsWith(mname)){
	                            nms=nms.substring(mname.length()+1);
	                            map.put( nms, as);
	                        }
	                        
	                    }
	                }
	            }
	        } catch (InstantiationException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	        return  t;
	    }
}

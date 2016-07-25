package com.hl.shiro;


import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.hl.entity.Hl_User;
import com.hl.mapper.Hl_UserMapper;
import com.hl.service.Hl_UserService;



/**
 * 自定义Realm,进行数据源配置
 * 
 * @author lanyuan 2014-12-25
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
public class MyRealm extends AuthorizingRealm {


    @Resource
    private Hl_UserService hl_UserService;
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		return null;
	}
	
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    String username=(String)token.getPrincipal();
	    Hl_User usermsg=new Hl_User();
	    usermsg.put("LoginName", "" + username + "");
	    List<Hl_User> userFormMaps=null;
	    try {
	        userFormMaps = hl_UserService.findByNames(usermsg);
        } catch (Exception e) {
            // TODO: handle exception
           e.printStackTrace();
        }
	    if(userFormMaps.size()!=0){
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
                    userFormMaps.get(0).get("LoginPassword"), // 密码
                    ByteSource.Util.bytes(""),
                    getName() // realm name
            );
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", userFormMaps.get(0));
            session.setAttribute("userSessionId", userFormMaps.get(0).get("ID"));
            return authenticationInfo;
	    }else {
            throw new UnknownAccountException();// 没找到帐号
        }
	}
	/**
     * 更新用户授权信息缓存.
     */
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
     * 更新用户信息缓存.
     */
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 清除用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 清除用户信息缓存.
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}
	
	/**
	 * 清空所有缓存
	 */
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}


	/**
	 * 清空所有认证缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
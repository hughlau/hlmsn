package com.hl.service.impl;


import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.hl.entity.Hl_User;
import com.hl.service.Hl_UserService;




@Service("hl_UserService")
public class Hl_UserServiceImpl extends BaseServiceImpl implements Hl_UserService{

	public void addUser(Hl_User usermap) {
		// TODO Auto-generated method stub
		String password=usermap.getStr("LoginPassword");
		String salt=new SecureRandomNumberGenerator().nextBytes().toHex();
		usermap.set("Salt", salt);
		String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(salt), 2).toHex();
		usermap.set("LoginPassword", newPassword);
		try {
			addEntity(usermap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

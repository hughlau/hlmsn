package com.hl.service.impl;


import org.springframework.stereotype.Service;

import com.hl.entity.Hl_User;
import com.hl.service.Hl_UserService;
import com.hl.util.CiphertextUtil;
import com.hl.util.NumberUtil;

@Service("hl_UserService")
public class Hl_UserServiceImpl extends BaseServiceImpl<Hl_User> implements Hl_UserService{

	public void addUser(Hl_User usermap) {
		// TODO Auto-generated method stub
		String password=usermap.getStr("LoginPassword");
		String salt=NumberUtil.getUUID();
		usermap.set("Salt", salt);
		password=CiphertextUtil.passAlgorithmsCiphering(password, salt, "SHA384");
		usermap.set("LoginPassword", password);
		try {
			addEntity(usermap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

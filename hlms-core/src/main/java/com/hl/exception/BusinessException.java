package com.hl.exception;

import com.hl.util.PropertiesUtil;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String frdMessage)  
    {  
        super(createFriendlyErrMsg(frdMessage));  
    }  
  
    public BusinessException(Throwable throwable)  
    {  
        super(throwable);  
    }  
  
    public BusinessException(Throwable throwable, String frdMessage)  
    {  
        super(throwable);  
    }  
  
    private static String createFriendlyErrMsg(String msgBody)  
    {  
        String prefixStr = "抱歉，";  
        String suffixStr = " 请稍后再试或与管理员联系！";  
  
        StringBuffer friendlyErrMsg = new StringBuffer("");  
  
        friendlyErrMsg.append(prefixStr);  
  
        String path = "/props/message.properties";
		String v = PropertiesUtil.getValue(path, "exception."+msgBody);
		if(v==null || v.equals("")){
			v=PropertiesUtil.getValue(path, "exception.all");
		}
		friendlyErrMsg.append(v);
        friendlyErrMsg.append(suffixStr);  
  
        return friendlyErrMsg.toString();  
    }
}

package com.hl.controller.dwr;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;




import javax.servlet.ServletException;

import org.apache.commons.lang.time.StopWatch;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.wltea.analyzer.sample.IKAnalzyerTFIDF;







import com.hl.dwr.DwrScriptSessionManagerUtil;

import com.hl.util.SpringBeanFactoryUtils;


public class PushMsg{

	
	
	public void onPageLoad(String userId) {  
		  
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();  
  
        scriptSession.setAttribute(userId, userId);  
  
        DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();  
  
        try {  
  
            dwrScriptSessionManagerUtil.init();  
  
        } catch (ServletException e) {  
  
            e.printStackTrace();  
  
        }  
  
    } 
	
	@SuppressWarnings("static-access")
	public void sendMessageAuto(String userid, String message) {  
		  
		try {
			System.out.println("==========调用了send方法==========");  
			ScriptBuffer scriptBuffer = new ScriptBuffer(); //构造js脚本  
			ScriptSession myScSession = WebContextFactory.get().getScriptSession();  
			scriptBuffer.appendScript("showMessage(");  
			scriptBuffer.appendData(message);  
			scriptBuffer.appendScript(")");  
			Util util = new Util(myScSession);  
			util.addScript(scriptBuffer); //向客户端推送消息  

			scriptBuffer = new ScriptBuffer(); //构造js脚本 
			scriptBuffer.appendScript("showMessage(");  
			scriptBuffer.appendData("end");  
			scriptBuffer.appendScript(")"); 
			util.addScript(scriptBuffer);
		} catch (Exception e) {
			// TODO: handle exception
		}
  
    }
	
	public void Tfidf(){
		
		StopWatch allwatch = new StopWatch();
		allwatch.start();
		
		ScriptBuffer scriptBuffer = new ScriptBuffer(); //构造js脚本  
		ScriptSession myScSession = WebContextFactory.get().getScriptSession();  
		scriptBuffer.appendScript("showMessage(");  
		scriptBuffer.appendData("-------------------------------------------开始分析-------------------------------------------------------\n开始抽取数据！");  
		scriptBuffer.appendScript(")");  
		Util util = new Util(myScSession);  
		util.addScript(scriptBuffer); //向客户端推送消息  
		StopWatch watch = new StopWatch();
		watch.start();
		
		/*GKG_CLGC dbmodel=new GKG_CLGC();
        dbmodel.put("where", " where 1=1 ");
		List<GKG_CLGC> list= gKG_CLGCService.findByWhere(dbmodel);*/
		
		 watch.stop();
		 Long time= watch.getTime();
		
		scriptBuffer = new ScriptBuffer(); //构造js脚本 
		scriptBuffer.appendScript("showMessage(");  
		scriptBuffer.appendData("\n抽取数据完成！！！(耗时"+String.valueOf(time)+")\n开始进行tf计算");  
		scriptBuffer.appendScript(")"); 
		util.addScript(scriptBuffer);
		
		
	}
}
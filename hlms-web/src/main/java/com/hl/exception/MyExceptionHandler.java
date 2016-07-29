package com.hl.exception;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



public class MyExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		//是否异步请求
		 if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                 .getHeader("X-Requested-With")!= null && request  
                 .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			
		 }else{
			 
		 }
		 
		 if(ex instanceof BusinessException)
		 {
			 model.put("msg", ex.getMessage());
		 }else{
			 model.put("msg", "服务器内部错误！");
		 }
		 return new ModelAndView("error/500",model);
	}

}

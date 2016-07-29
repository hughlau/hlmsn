package com.hl.controller.es;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/tool")
public class ToolController extends BaseController{

	@RequestMapping("/head")
	public String ui_head(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/tool/head";
	}
	
	@RequestMapping("/api")
	public String ui_api(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/tool/api";
	}
	
	@RequestMapping("/query")
	public String ui_query(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/tool/query";
	}
}

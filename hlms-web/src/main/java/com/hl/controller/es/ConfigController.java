package com.hl.controller.es;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/import/config")
public class ConfigController  extends BaseController{

	@RequestMapping("/es")
	public String ui_config_es(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/import/es";
	}
	
	@RequestMapping("/db")
	public String ui_config_db(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/import/db";
	}
	
	@RequestMapping("/mapping")
	public String ui_config_mapping(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/import/mapping";
	}
}

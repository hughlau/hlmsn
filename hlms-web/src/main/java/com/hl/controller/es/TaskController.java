package com.hl.controller.es;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/import/task")
public class TaskController  extends BaseController{

	@RequestMapping("/manage")
	public String ui_manage(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/import/manage";
	}
	
	@RequestMapping("/run")
	public String ui_run(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/import/run";
	}
}

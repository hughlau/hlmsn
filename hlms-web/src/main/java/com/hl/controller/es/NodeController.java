package com.hl.controller.es;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/node")
public class NodeController extends BaseController{

	@RequestMapping("")
	public String ui_node(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/message/node";
	}
}

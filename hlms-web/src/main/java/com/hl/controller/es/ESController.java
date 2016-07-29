package com.hl.controller.es;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/es")
public class ESController extends BaseController{

	

	@RequestMapping(value = "")
	public String ui(HttpServletRequest request) {
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/index";
	}

	
}

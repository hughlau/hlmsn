package com.hl.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DefaultController {

	@RequestMapping(value="index")
	public String ui_index(HttpServletRequest request){
		return "admin/index";
	}
}

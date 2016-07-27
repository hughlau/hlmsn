package com.hl.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class PageController {

	@RequestMapping(value="/index")
	public String ui_index(){
		return "pages/cms/index";
	}
}

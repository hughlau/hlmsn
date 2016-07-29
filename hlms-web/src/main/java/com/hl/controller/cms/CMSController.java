package com.hl.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/cms")
public class CMSController {

	@RequestMapping(value="/index")
	public String ui_index(){
		return "pages/cms/index";
	}
}

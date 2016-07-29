package com.hl.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/sys")
public class SYSController {

	@RequestMapping(value="/index")
	public String ui_index(){
		return "pages/gis/index";
	}
}

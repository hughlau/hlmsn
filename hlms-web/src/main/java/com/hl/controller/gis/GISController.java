package com.hl.controller.gis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/gis")
public class GISController {

	@RequestMapping(value="/index")
	public String ui_index(){
		return "pages/gis/index";
	}
}

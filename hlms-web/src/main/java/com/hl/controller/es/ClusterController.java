package com.hl.controller.es;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es/cluster")
public class ClusterController extends BaseController{

	@RequestMapping("")
	public String ui_cluster(HttpServletRequest request){
		request.setAttribute("modules", getESHeader());
		request.setAttribute("title", title);
		return "pages/es/message/cluster";
	}
}

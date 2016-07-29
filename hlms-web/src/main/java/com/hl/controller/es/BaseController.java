package com.hl.controller.es;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hl.entity.Hl_module;
import com.hl.service.Hl_moduleService;

public class BaseController {

	public HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();;
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
	public String title="ES";
	@Resource
	private Hl_moduleService hl_moduleService;
	
	public String getESHeader(){
		Hl_module es = hl_moduleService.findbyFrist("name", "ES",
				Hl_module.class);
		List<Hl_module> modules = hl_moduleService.findByAttribute("moduleid",
				es.getInt("id").toString(), Hl_module.class);
		String html = loopHeader(modules, 0, "");
		return html;
	}
	
	
	public String loopHeader(List<Hl_module> list, int parentId, String html) {
		
		html += "<ul>";
		for (Hl_module e : list) {
			if (e.getInt("parentId") == parentId && e.getInt("ishide") == 0) {

				html += "<li>";
				
				if (e.getStr("resUrl") == null
						|| e.getStr("resUrl").equals("")) {
					html += "<a href=\"javascript:;\">"
							+ e.getStr("name") + "</a>";
					html = loopHeader(list, e.getInt("id"), html);
				}else{
					html += "<a href=\"" +basePath+ e.getStr("resUrl") + "\">"
							+ e.getStr("name") + "</a>";
				}
				html += "</li>";
			}
		}
		html += "</ul>";
		return html;
	}
}

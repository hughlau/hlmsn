package com.hl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hl.entity.Hl_User;
import com.hl.service.Hl_UserService;

@Controller
@RequestMapping("/")
public class InitController {

	@Resource
    private Hl_UserService hl_UserService;
	
	@RequestMapping(value="login")
	public String ui_login(HttpServletRequest request){
		request.removeAttribute("error");
		return "login";
	}
	
	@RequestMapping(value="register")
	public String ui_register(HttpServletRequest request){
		return "register";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String login(String username,String password,HttpServletRequest request){
		Hl_User usermsg=new Hl_User();
	    usermsg.put("LoginName", "" + username + "");
		 List<Hl_User>  userFormMaps = hl_UserService.findByNames(usermsg);
		try {
            if ("".equals(username.trim()) || "".equals(password.trim())) {
                request.setAttribute("error", "用户名或密码不能为空！");
                return "/login";
            }
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                user.login(token);
            }catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", "用户或密码不正确！");
                return "/login";
            }

            Session session = SecurityUtils.getSubject().getSession();

            request.removeAttribute("error");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "系统异常，请联系管理员！");
            return "/login";
        }
        
        return "redirect:admin/index";
	}
	
	@ResponseBody
	@RequestMapping(value="register",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String register(String username,String password,String email,String phone){
		return "";
	}
	
	
	
	@RequestMapping(value="index")
	public String indexUI(){
		return "index";
	}
}

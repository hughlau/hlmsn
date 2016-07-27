package com.hl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hl.entity.Hl_User;
import com.hl.service.Hl_UserService;
import com.hl.util.PropertiesUtil;
import com.hl.validate.entity.User_Register;


@Controller
@RequestMapping("/")
public class InitController extends BaseController{

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
	public String login(String username,String password,String remember,HttpServletRequest request){

		try {
            if ("".equals(username.trim()) || "".equals(password.trim())) {
                request.setAttribute("error", getTipMsg("login.null"));
                return "/login";
            }
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if(remember!=null && remember.equals("true")){
            	token.setRememberMe(true);
            }
            try {
                user.login(token);
            }catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", getTipMsg("login.wrong"));
                return "/login";
            }

            Session session = SecurityUtils.getSubject().getSession();

            request.removeAttribute("error");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", getTipMsg("connection.error"));
            return "/login";
        }
        
        return "redirect:admin/index";
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET,produces="text/html;charset=utf-8")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "index";
    }
	

	@RequestMapping(value="register",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	public String register(@Valid User_Register user,BindingResult result){
		if (result.hasErrors())
		{
			List<ObjectError> errors= result.getAllErrors();
			for (int i = 0; i < errors.size(); i++) {
				ObjectError obj=errors.get(i);
				String a= obj.getDefaultMessage();
				System.out.println(a);
			}
			return "register"; 
		}else{
			Hl_User item=new Hl_User();
			item.set("LoginName", user.getUsername());
			item.set("LoginPassword", user.getPassword());
			item.set("Email", user.getEmail());
			item.set("Phone", user.getPhone());
			item.set("CreateUser","0");
			hl_UserService.addUser(item);;
		} 
		return "login";
	}
	
	
	
	@RequestMapping(value="index")
	public String indexUI(){
		return "index";
	}
	
	@RequestMapping(value="")
	public String indexUI1(){
		return "index";
	}
}

require.config({
	baseUrl:'res',
    paths: {
        jquery: 'lib/jquery-1.10.1.min',
		bootstrap:'lib/bootstrap.min',
		validate:'plugins/validation/jquery.validate.min',
		validate_add:'plugins/validation/additional-methods.min',
		app:'lib/app'
    },
	shim:{
		"bootstrap":{
			deps:["jquery"]
		},
		"validate":{
			deps:["jquery"]
		},
		"validate_add":{
			deps:["jquery","validate"]
		},
		enforceDefine: true
	}
});

require(['jquery','bootstrap','app','validate','validate_add'], function($) {

	$(function(){
		App.init();
		
		var validate = $("#register").validate({
		    focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
		    onkeyup: false,   
		    rules:{
		    	username:{
		            required:true
		        },
		        password:{
		            required:true,
		            rangelength:[4,20]
		        },
		        rpassword:{
		            required:true,
		            equalTo: "#register_password",   
					rangelength:[4,20]
		        },
		        email:{
		            required:true,
		            email: true
		        },
		        phone:{
		            required:true,
		            rangelength:[11,11],
					digits: "只能输入整数"
		        },
		    },
		    messages:{
		    	username:{
		            required:"必填"
		        },
		        password: {
					required: "请输入密码",
					rangelength: jQuery.format("密码在8~20个字符之间"),
				},
				rpassword: {
					required: "请输入确认密码",
					rangelength: jQuery.format("密码在8~20个字符之间"),
					equalTo: "两次输入密码不一致"
				},
				email:{  
				    required: "请输入Email地址",  
				    email: "请输入正确的email地址"  
				},
				phone:{
					required: "请输入手机号",
					rangelength: jQuery.format("请输入正确的手机号"),
				},
		    }
		              
		}); 
		
		$("#btn_login").click(function(){
			window.location.href=basePath+"login";
		});
	});
	
	 

});




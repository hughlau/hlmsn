require.config({
	baseUrl:'res',
    paths: {
        jquery: 'lib/jquery-1.10.1.min',
		bootstrap:'lib/bootstrap.min',
		app:'lib/app'
    },
	shim:{
		"bootstrap":{
			deps:["jquery"]
		},
		enforceDefine: true
	}
});

require(['jquery','bootstrap','app'], function($) {

	$(function(){
		App.init();
		$("#nav_title>li>a").click(function(){
			$("#nav_title>li>a").each(function(){
			    var selected="<span class='selected'></span>";
				$(this).find("span").remove();
				$(this).parent("li").removeClass("active");
			});
			$(this).parent("li").addClass("active");
			$(this).append("<span class='selected'></span>");
		})
	})
	
});



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
	})
	
});



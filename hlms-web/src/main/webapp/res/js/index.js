require.config({
	baseUrl:'res',
    paths: {
        jquery: 'lib/jquery-1.10.1.min',
		bootstrap:'lib/bootstrap.min',
		modernizr:'lib/modernizr.custom.72241'
    },
	shim:{
		"bootstrap":{
			deps:["jquery"]
		},
		"modernizr":{
			deps:["jquery"]
		},
		enforceDefine: true
	}
});

require(['jquery','bootstrap','modernizr'], function($) {

	// global. currently active menu item 
	var current_item = 0;

	// few settings
	var section_hide_time = 500;
	var section_show_time = 500;

	$(function(){
		// Switch section
		$("a", '.dropdown').click(function() 
		{
			if( ! $(this).hasClass('active') ) { 
				current_item = this;
				// close all visible divs with the class of .section
				$('.section:visible').fadeOut( section_hide_time, function() { 
					$('a', '.dropdown').removeClass( 'active' );  
					$(current_item).addClass( 'active' );
					var new_section = $( $(current_item).attr('href') );
					new_section.fadeIn( section_show_time );
				} );
			}
			return false;
		});	
		
	})
	
	
});



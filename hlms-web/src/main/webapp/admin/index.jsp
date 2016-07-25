<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>Metronic | Layouts - Horzontal & Sidebar Menu</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="../res/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="../res/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="../res/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<link rel="shortcut icon" href="../res/image/favicon.ico" />

	<style>
	
	.nav li a{

		line-height:40px;
	}
	.nav li li a{
		line-height:20px;
	}
	</style>
	<script data-main="res/js/main" src="res/lib/require.js"></script>
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">

				<!-- BEGIN LOGO -->

				<a class="brand" href="myindex.html" style="color:yellow;font-size:50px;">

				CUSSM

				</a>

				<!-- END LOGO -->

				<!-- BEGIN HORIZANTAL MENU -->

				<div class="navbar hor-menu hidden-phone hidden-tablet">

					<div class="navbar-inner">

						<ul class="nav" id="nav_title">

							

							<li class="active">

								<a href="javascript:;">

								用户管理

								<span class="selected"></span>

								</a>

							</li>

							

							<li>

								<a href="javascript:;">菜单管理</a>

								<span></span>

								</a>
							</li>

							
							
							<li>

								<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">

								功能管理

								<span class="arrow"></span>     

								</a>

								<ul class="dropdown-menu">

									<li><a href="#">CMS</a></li>

									<li><a href="#">GIS</a></li>

									<li><a href="#">ES</a></li>

									<li><a href="#">微信</a></li>

								</ul>

								<b class="caret-out"></b>                        

							</li>
							
							<li>

								<a href="javascript:;">服务管理</a>

								<span></span>

								</a>
							</li>

							<li>

								<a href="javascript:;">系统管理</a>

								<span></span>

								</a>
							</li>

						</ul>

					</div>

				</div>

				<!-- END HORIZANTAL MENU -->

				<!-- BEGIN RESPONSIVE MENU TOGGLER -->

				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

				<img src="res/image/menu-toggler.png" alt="" />

				</a>          

				<!-- END RESPONSIVE MENU TOGGLER -->            

				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">

					

					

					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user">

						<a href="#" class="dropdown-toggle" data-toggle="dropdown">

						<img alt="" src="res/image/avatar1_small.jpg" />

						<span class="username">Bob Nilson</span>

						<i class="icon-angle-down"></i>

						</a>

						<ul class="dropdown-menu">

							<li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>

							<li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>

							<li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>

							<li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>

							<li class="divider"></li>

							<li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>

							<li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>

						</ul>

					</li>

					<!-- END USER LOGIN DROPDOWN -->

				</ul>

				<!-- END TOP NAVIGATION MENU --> 

			</div>

		</div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->   

	<div class="page-container row-fluid" >

		<!-- BEGIN HORIZONTAL MENU PAGE SIDEBAR1 -->

		<div class="page-sidebar nav-collapse collapse">

			<ul class="page-sidebar-menu hidden-phone hidden-tablet">

				<li>

					<a style="font-size:20px;font-weight:bold;">菜单</a>

				</li>

				

				<li class="start active">

					<a href="javascript:;">

					<i class="icon-cogs"></i> 

					<span class="title">Layouts</span>

					<span class="selected "></span>

					<span class="arrow open"></span>

					</a>

					<ul class="sub-menu">

						<li class="active">

							<a href="layout_horizontal_sidebar_menu.html">

							Horzontal & Sidebar Menu                     </a>

						</li>

						<li >

							<a href="layout_horizontal_menu1.html">

							Horzontal Menu 1                    </a>

						</li>

						<li >

							<a href="layout_horizontal_menu2.html">

							Horzontal Menu 2                    </a>

						</li>

						<li >

							<a href="layout_promo.html">

							Promo Page                    </a>

						</li>

						<li >

							<a href="layout_email.html">

							Email Templates                     </a>

						</li>

						<li >

							<a href="layout_ajax.html">

							Content Loading via Ajax</a>

						</li>

						<li >

							<a href="layout_sidebar_closed.html">

							Sidebar Closed Page                    </a>

						</li>

						<li >

							<a href="layout_blank_page.html">

							Blank Page                    </a>

						</li>

						<li >

							<a href="layout_boxed_page.html">Boxed Page</a>

						</li>

						

					</ul>

				</li>

				

				<li >

					<a href="javascript:;">

					<i class="icon-map-marker"></i> 

					<span class="title">Maps</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">

						<li >

							<a href="maps_google.html">

							Google Maps                   </a>

						</li>

						<li >

							<a href="maps_vector.html">

							Vector Maps                   </a>

						</li>

					</ul>

				</li>

				<li >

					<a href="charts.html">

					<i class="icon-bar-chart"></i> 

					<span class="title">Visual Charts</span>

					</a>

				</li>

				<li class="last">

					<a href="login.html">

					<i class="icon-user"></i> 

					<span class="title">Login Page</span>

					</a>

				</li>

			</ul>

			<!--HORIZONTAL AND SIDEBAR MENU FOR MOBILE & TABLETS-->

			<ul class="page-sidebar-menu visible-phone visible-tablet">

				<li>

					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->

					<form class="sidebar-search">

						<div class="input-box">

							<a href="javascript:;" class="remove"></a>

							<input type="text" placeholder="Search..." />            

							<input type="button" class="submit" value=" " />

						</div>

					</form>

					<!-- END RESPONSIVE QUICK SEARCH FORM -->

				</li>

				<li class="active">

					<a href="index.html">

					Dashboard

					<span class="selected"></span>

					<span class="arrow open"></span>

					</a>

					<ul class="sub-menu">

						<li class="active">

							<a href="javascript:;">

							<i class="icon-cogs"></i> 

							<span class="title">Layouts</span>

							<span class="arrow open"></span>

							</a>

							<ul class="sub-menu">

								<li class="active">

									<a href="layout_horizontal_sidebar_menu.html">

									Horzontal & Sidebar Menu                     </a>

								</li>

								<li >

									<a href="layout_horizontal_menu1.html">

									Horzontal Menu 1                    </a>

								</li>

								<li >

									<a href="layout_horizontal_menu2.html">

									Horzontal Menu 2                    </a>

								</li>

								<li >

									<a href="layout_promo.html">

									Promo Page                    </a>

								</li>

								<li >

									<a href="layout_email.html">

									Email Templates                     </a>

								</li>

								<li >

									<a href="layout_sidebar_closed.html">

									Sidebar Closed Page                    </a>

								</li>

								<li >

									<a href="layout_blank_page.html">

									Blank Page                    </a>

								</li>

								<li >

									<a href="layout_boxed_page.html">Boxed Page</a>

								</li>

								<li >

									<a href="layout_boxed_not_responsive.html">

									Non-Responsive Boxed Layout                     </a>

								</li>

							</ul>

						</li>

						<li >

							<a href="javascript:;">

							<i class="icon-th-list"></i> 

							<span class="title">Portlets</span>

							<span class="arrow "></span>

							</a>

							<ul class="sub-menu">

								<li >

									<a href="portlet_general.html">

									General Portlets                    </a>

								</li>

								<li >

									<a href="portlet_draggable.html">

									Draggable Portlets                     </a>

								</li>

							</ul>

						</li>

						<li >

							<a href="javascript:;">

							<i class="icon-map-marker"></i> 

							<span class="title">Maps</span>

							<span class="arrow "></span>

							</a>

							<ul class="sub-menu">

								<li >

									<a href="maps_google.html">

									Google Maps                   </a>

								</li>

								<li >

									<a href="maps_vector.html">

									Vector Maps                   </a>

								</li>

							</ul>

						</li>

						<li >

							<a href="charts.html">

							<i class="icon-bar-chart"></i> 

							<span class="title">Visual Charts</span>

							</a>

						</li>

						<li >

							<a href="login.html">

							<i class="icon-user"></i> 

							<span class="title">Login Page</span>

							</a>

						</li>

					</ul>

				</li>

				<li>

					<a href="javascript:;">

					Sections

					<span class="arrow"></span>     

					</a>

					<ul class="sub-menu">

						<li><a href="#">Section 1</a></li>

						<li><a href="#">Section 2</a></li>

						<li><a href="#">Section 3</a></li>

						<li><a href="#">Section 4</a></li>

						<li><a href="#">Section 5</a></li>

					</ul>

				</li>

				<li>

					<a href="">Tables</a>

				</li>

				<li>

					<a href="">Extra

					<span class="arrow"></span>

					</a>

					<ul class="sub-menu">

						<li><a href="index.html">About Us</a></li>

						<li><a href="index.html">Services</a></li>

						<li><a href="index.html">Pricing</a></li>

						<li><a href="index.html">FAQs</a></li>

						<li><a href="index.html">Gallery</a></li>

						<li><a href="index.html">Registration</a></li>

					</ul>

				</li>

			</ul>

		</div>

		<!-- END BEGIN HORIZONTAL MENU PAGE SIDEBAR -->

		<!-- BEGIN PAGE -->

		<div class="page-content">

			




		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			2013 &copy; Metronic by keenthemes.

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	

	
	
<!-- END BODY -->

</html>
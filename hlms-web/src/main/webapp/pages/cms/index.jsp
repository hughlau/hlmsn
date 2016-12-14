<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Slate Theme, About Our Company</title>
<link href="../res/css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="../res/css/nivo-slider.css" type="text/css" media="screen" />

<script language="javascript" type="text/javascript">
function clearText(field){

    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>

<link rel="stylesheet" type="text/css" href="../res/css/ddsmoothmenu.css" />

<script type="text/javascript" src="../res/js/jquery.min.js"></script>
<script type="text/javascript" src="../res/js/ddsmoothmenu.js">

/***********************************************
* Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

</head>

<body>

<div id="templatemo_wrapper">
	<jsp:include page="../common/header.jsp" />
    
    <div id="templatemo_main">
    	<div id="content" class="float_l">
            <h2>Our History</h2>
            <p>Sed tincidunt auctor lorem eu ullamcorper. Suspendisse suscipit varius pulvinar. Nulla tempus sapien a est tincidunt lobortis. Pellentesque porta, magna <a href="#">ac vehicula facilisis</a>, nisl mi ultrices leo, non pellentesque tellus odio sed lectus. Quisque ultrices, dolor scelerisque pretium tempor, urna massa porttitor mi, nec malesuada est erat at justo. Proin rhoncus fermentum arcu scelerisque vestibulum.</p>
            <ul class="templatemo_list">
                <li>Suspendisse eget lacus ac ipsum aliquet iaculis faucibus non lacus.</li>
                <li>Pellentesque quis nulla id orci malesuada porta posuere quis massa.</li>
                <li>Nunc vitae purus non augue scelerisque ultricies vitae et velit quis.</li>
                <li>Cum sociis natoque penatibus et magnis dis parturient montes.</li>
                <li>Morbi a augue eget orci sodales blandit morbiet mi adipiscing.</li>
            </ul>
            <div class="cleaner h20"></div>
            <h3>Donec Venenatis Faucibus Lectus</h3>
            <p>Nam nec leo. Curabitur quis eros a arcu <a href="#">feugiat egestas</a>. Nunc sagittis, dui non porttitor tincidunt, mi tortor tincidunt sem, et aliquet mi tortor eu turpis. Ut nisi ligula, viverra ac, placerat sed, ultricies vitae, neque. Morbi feugiat neque non odio eleifend pulvinar. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
            <div class="cleaner"></div>
            <blockquote>Nam hendrerit ullamcorper gravida. Vivamus velit augue, molestie vitae commodo eget, sodales quis mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus nec tellus a dolor scelerisque semper sit amet at justo.
            </blockquote>
		</div>
        <div id="sidebar" class="float_r">
        	<div class="sb_box">
                <h3>Categories</h3>
                <ul class="sb_list">
                    <li><a href="#">At tincidunt nunc pretium</a></li>
                    <li><a href="#">Etiam vitae rutrum libero</a></li>
                    <li><a href="#">Donec faucibus accumsan</a></li>
                    <li><a href="#">Phasellus auctor rhoncus</a></li>
                    <li><a href="#">Ut facilisis feugiat diam</a></li>
                    <li><a href="#">Vestibulum vel et felis</a></li>
                </ul>
            </div>
            
            <div class="sb_box">
                <h3>Testimonial</h3>
                Aliquam sed eleifend mi. Aliquam et lacus vitae lectus faucibus iaculis nec at diam. Mauris mattis metus eget rutrum luctus. Vestibulum vel odio. Phasellus eget nisi dolor vulputate.	
                <div class="cleaner"></div>
                <cite>Richard <a href="#"><span>- Senior Webmaster</span></a></cite>
            </div>
        </div>
        <div class="cleaner"></div>
    </div>
    
    <jsp:include page="../common/footer.jsp" />
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="context.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Slate Theme, About Our Company</title>
<link href="<%=basePath%>pages/res/css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%=basePath%>pages/res/css/nivo-slider.css" type="text/css" media="screen" />

<script language="javascript" type="text/javascript">
function clearText(field){

    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/res/css/ddsmoothmenu.css" />

<script type="text/javascript" src="<%=basePath%>pages/res/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/res/js/ddsmoothmenu.js"></script>

<script type="text/javascript">
if ("${error}" != "") {
    alert("${error}");
};
ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>
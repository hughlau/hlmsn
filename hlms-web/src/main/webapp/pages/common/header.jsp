<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hl.entity.*"%>
<%@include file="context.jsp" %>
<div id="templatemo_header">
    	<div id="site_title"><h1><a href="javascript:;"><%out.print(request.getAttribute("title")); %></a></h1></div>
        <div class="cleaner"></div>
    </div> <!-- end of header -->
    <div id="templatemo_menu" class="ddsmoothmenu">
		<%out.print(request.getAttribute("modules")); %>
        <br style="clear: left" />
    </div>
    
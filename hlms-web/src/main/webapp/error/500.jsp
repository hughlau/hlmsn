<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>500</title>
<style type="text/css">
<!--
.t {
        font-family: Verdana, Arial, Helvetica, sans-serif;
        color: #CC0000;
}
.c {
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 11px;
        font-weight: normal;
        color: #000000;
        line-height: 18px;
        text-align: center;
        border: 1px solid #CCCCCC;
        background-color: #FFFFEC;
}
body {
        background-color: #FFFFFF;
        margin-top: 100px;
}
-->
</style>
</head>
<body>
<div align="center">
  <h2><span class="t">500</span></h2>
  <table border="0" cellpadding="8" cellspacing="0" width="460">
    <tbody>
      <tr>
        <td class="c"><%= e.getMessage()%></td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>
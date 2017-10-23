<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	<div class="errorPage">
		<%-- <h1>${errorMsg}</h1>  --%>
		<h1  style="color: #F00;">Error message: ${errorMsg} </h1>
		
		<img
			src="<c:url value="/resources/images/404errorPage.jpg" />"
			height="500px" width="970px" />
	</div>
</body>
</html>
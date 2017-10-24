<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing Report List</title>
</head>
<body>
	<table>
		<tr>
			<c:forEach var="assignment" items="${assignmentList}">
				<td><c:out value="${assignment.id}" /></td>
				<fmt:parseDate value="${assignment.start_date}"
					pattern="yyyy-MM-dd'T'HH:mm" var="startDate" type="date" />
				<fmt:formatDate value="${startDate}" var="myStartDate" type="date"
					pattern="MM/dd/yyyy hh:mm:ss a z" />
				<td>${myStartDate}</td>
				<fmt:parseDate value="${assignment.end_date}"
					pattern="yyyy-MM-dd'T'HH:mm" var="endDate" type="date" />
				<fmt:formatDate value="${endDate}" var="myEndDate" type="date"
					pattern="MM/dd/yyyy hh:mm:ss a z" />
					<td>${myEndDate}</td>
				<td><c:out value="${assignment.finished}" /></td>
				<td><c:out value="details" /></td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>
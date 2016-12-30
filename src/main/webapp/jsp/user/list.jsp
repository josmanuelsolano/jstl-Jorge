<%@page import="com.softtek.academy.jstl.service.UserService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%!
	UserService userService = new UserService();
%>
<%
	request.setAttribute("user", userService.list());
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Carts</title>
	<meta charset="UTF-8">
</head>
<body style="font-family: sans-serif;">
	<h3 style="color: blue; font-family: sans-serif;">User List:</h3>
	<br/>
	<table border="1" style="width: 50%;">
		<tr>
			<th width="20%">Username</th>
			<th width="10%">name</th>
			<th width="10%">Role</th>
			<th width="10%">Active</th>
		</tr>
		<c:forEach var="user" items="${user}">
			<tr>
				<td><a href="<c:url value="/jsp/user/edit.jsp?username=${user.username}"/>">${user.username}</a></td>
				<td>${user.name}</td>
				<td>${user.userRole.description}</td>
				<td>${user.active}</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value="/index.jsp"/>">Return to Index</a>
	
</body>
</html>
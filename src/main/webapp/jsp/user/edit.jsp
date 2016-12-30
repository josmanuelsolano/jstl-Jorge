<%@page import="com.softtek.academy.jstl.service.UserRoleService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.softtek.academy.jstl.domain.model.UserRole"%>
<%@page import="java.util.List"%>
<%@page import="com.softtek.academy.jstl.domain.model.User"%>
<%@page import="com.softtek.academy.jstl.service.UserService"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
	UserService userService = new UserService();
	UserRoleService userRoleService = new UserRoleService();
%>
<%
	String msg = "";

	
	User user = userService.findOne(new String(request.getParameter("username")));
	
	
	if (request.getParameter("update") != null) {
		
		final String username = request.getParameter("username");
		final String name = 
				StringUtils.isNotEmpty(request.getParameter("name")) 
						? request.getParameter("name") : null;
		final String password = 
				StringUtils.isNotEmpty(request.getParameter("password")) 
						? request.getParameter("password") : null;
		final String role = 
				StringUtils.isNotEmpty(request.getParameter("role")) 
						? request.getParameter("role") : null;
		final String active = request.getParameter("active");
		// createUser, createDate, updateUser and updateDate will be updated in cartService.update();
		
		user.setUsername(username);
		user.setName(name);
		user.setPassword(password);
		user.setUserRole(new UserRole(role));
		user.setActive(active);
		
		Boolean userUpdated = userService.update(user);
		
		if (userUpdated) {
			
			response.sendRedirect(request.getContextPath() + "/jsp/user/list.jsp");
		}
		else {
	
			msg = "Please check the required fields.";
		}
	}
	
	List<UserRole> userRoles = userRoleService.list();
	
	request.setAttribute("user", user);
	request.setAttribute("userRoles", userRoles);
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>States</title>
	<meta charset="UTF-8">
</head>
<body style="font-family: sans-serif;">
	<h3 style="color: blue; font-family: sans-serif;">Update User</h3>
	
	<form action="" method="post">
		<table style="width: 40%;">
			<tr>
				<td width="13%">Username</td>
				<td width="25%">
					<input type="text" name="username" value="${user.username}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td width="10%">Password:*</td>
				<td width="25%">
					<input type="password" name="password" value="">
				</td>
			</tr>
			<tr>
				<td>Name:*</td>
				<td>
					<input type="text" name="name" value="${user.name}">
				</td>
			</tr>
			<tr>
				<td>Role:*</td>
				<td>
					<select name="role">
						<c:forEach var="userRole" items="${userRoles}">
							<option value="${userRole.id}" <c:if test="${userRole.id==user.userRole.id}">selected</c:if>>${userRole.description}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Active:*</td>
				<td>
					<select name="active">
						<option value="S" <c:if test="${'S'==user.active}">selected</c:if>>S</option>
						<option value="N" <c:if test="${'N'==user.active}">selected</c:if>>N</option>
					</select>
				</td>
			</tr>
		</table>
		
		<br>
		<input type="submit" name="update" value="Update">
	</form>
	
	<br>
	<div><%= msg %></div>
	
	<br>
	<a href="<c:url value="/jsp/cart/list.jsp"/>">Return to List</a>
</body>
</html>
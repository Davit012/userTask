<%@ page import="managers.UserManager" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        response.sendRedirect("/home");
    }
    %>
<% String msg = (String) session.getAttribute("msg");%>
<% if (msg != null && !"".equals(msg)) { %>
<span><%=msg%></span>
<%
        session.removeAttribute("msg");
    }
%>
<h1>Login</h1>
<form action="/login" method="post">
    email: <input type="text" name="email"/> <br>
    password: <input type="password" name="password"> <br>
    <input type="submit" value="login">
</form>
</body>
</html>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="managers.TaskManager" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.07.2021
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
<a href="/addUser">Add User</a> | <a href="/logout">Logout</a>

<%String msg = (String) session.getAttribute("msg");%>
<% if (msg != null && !"".equals(msg)) { %>
<span><%=msg%></span>
<%
        session.removeAttribute("msg");
    }%>
<% TaskManager taskManager = new TaskManager(); %>
<%= taskManager.getTasks()%>

</body>
</html>

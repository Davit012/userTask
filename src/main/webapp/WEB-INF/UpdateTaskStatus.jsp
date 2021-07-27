<%@ page import="com.example.userTasks.enums.TaskStatus" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.07.2021
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Task</title>
</head>
<body>
<form action="/updateStatus" method="post" style="margin: auto">
    <%
        int id = Integer.parseInt(request.getParameter("id"));%>
    <input type="hidden" name="id" value="<%=id%>">
    Add Task:<br>
    Task name:<input type="readonly" name="name" value=""><br>
    Task description:<input type="readonly " name="description"><br>

    Task status:<select name="status">
    <option><%=TaskStatus.values()[0]%>
    </option>
    <option><%=TaskStatus.values()[1]%>
    </option>
    <option><%=TaskStatus.values()[2]%>
    </option>
</select><br>
    Task deadline:<input type="readonly" name="date"/><br>
    <input type="submit" value="Add Task">
</form>
</body>
</html>

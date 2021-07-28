<%@ page import="models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.userTasks.enums.TaskStatus" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23.07.2021
  Time: 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
| <a href="/logout">Logout</a>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>User</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>Change Status</th>
    </tr>
    <tr style="border: 1px solid black;">
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks != null && !tasks.isEmpty()) {
                for (Task task : tasks) {
        %>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getUser()%>
        </td>
        <td>
            <%=task.getStatus()%>
            <br>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td>
            <form action="/updateStatus" method="post">
                <input type="hidden" name="id" value="<%=task.getId()%>">
                <select name="status">
                    <option><%=TaskStatus.values()[0]%>
                    </option>
                    <option><%=TaskStatus.values()[1]%>
                    </option>
                    <option><%=TaskStatus.values()[2]%>
                    </option>
                </select><br>
                <input type="submit" value="CHANGE">
            </form>
        </td>

    </tr>
    <%
            }

        }
    %>
</table>
</body>
</html>

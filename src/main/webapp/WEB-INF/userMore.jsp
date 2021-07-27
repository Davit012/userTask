<%@ page import="models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="com.example.userTasks.enums.TaskStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>More</title>
</head>
<body>
| <a href="/logout">Logout</a>
<form action="/addTask" method="post" style="margin: auto">
    <%
        int id = Integer.parseInt(request.getParameter("id"));%>
    <input type="hidden" name="id" value="<%=id%>">
    Add Task:<br>
    Task name:<input type="text" name="name"><br>
    Task description:<input type="text" name="description"><br>

    Task status:<select name="status">
    <option><%=TaskStatus.values()[0]%>
    </option>
    <option><%=TaskStatus.values()[1]%>
    </option>
    <option><%=TaskStatus.values()[2]%>
    </option>
</select><br>
    Task deadline:<input type="date" name="date"/><br>
    <input type="submit" value="Add Task">
</form>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>User</th>
        <th>Status</th>
        <th>Deadline</th>
    </tr>
    <tr style="border: 1px solid black;">
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks != null && !tasks.isEmpty()) {
                for (Task task : tasks) {
                    System.out.println(task);

        %>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getUser()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>

    </tr>
    <%
            }

        }
    %>
</table>
</body>
</html>

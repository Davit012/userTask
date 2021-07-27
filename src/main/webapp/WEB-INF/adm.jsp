<%@ page import="models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>
<% String msg = (String) request.getSession().getAttribute("msg");
    if (msg != null && msg != "") {
%>
<%=msg%>
<%
    }
%>


<br>
Add user: | <a href="/logout">Logout</a><br>
<form action="/adduser" method="post" style="margin: auto">
    Name:<input type="text" name="name"><br>
    Surname:<input type="text" name="surname"><br>
    Email:<input type="text" name="email"><br>
    Password:<input type="password" name="password"><br>
    Re-Password<input type="password" name="re-password"><br>
    <input type="submit" value="Add user">
</form>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>email</th>
        <th>type</th>
        <th>Action</th>
    </tr>
    <tr style="border: 1px solid black;">
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {

        %>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getType()%>
        </td>
        <td><a href="/more?id=<%=user.getId()%>">More Info</a> |
            <a href="/deleteUser?id=<%=user.getId()%>">Delete User</a>

        </td>

    </tr>
    <%
            }

        }
    %>
</table>

</body>
</html>

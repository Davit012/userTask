package servlets;

import managers.TaskManager;
import models.Task;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/more")
public class UserMoreServlet extends HttpServlet {
    TaskManager taskManager = new TaskManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Task> tasks = taskManager.getTasksByUser(id);
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/userMore.jsp").forward(req, resp);
    }
}

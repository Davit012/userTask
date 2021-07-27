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

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        int id = user.getId();
        List<Task> tasks = taskManager.getTasksByUser(id);
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);

    }
}


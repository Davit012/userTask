package servlets;

import com.example.userTasks.enums.TaskStatus;
import lombok.SneakyThrows;
import managers.TaskManager;
import managers.UserManager;
import models.Task;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    TaskManager taskManager = new TaskManager();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TaskStatus statusEnum = TaskStatus.valueOf(req.getParameter("status"));
        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        TaskStatus status = statusEnum;
        Date date = sdf.parse(req.getParameter("date"));

        Task task = Task.builder()
                .name(name)
                .description(description)
                .user(userManager.getUserById(id))
                .status(status)
                .deadline(date)
                .build();
        taskManager.addTask(task);
        req.getSession().setAttribute("msg", "Task was added successfully");
        resp.sendRedirect("/adm");
    }
}

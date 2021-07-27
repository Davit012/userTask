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

@WebServlet(urlPatterns = "/updateStatus")
public class UpdateStatusServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskStatus statusEnum = TaskStatus.valueOf(req.getParameter("status"));
        int taskId = Integer.parseInt(req.getParameter("id"));
        Task taskById = taskManager.getTaskById(taskId);
        if (taskById != null) {
            TaskStatus status = statusEnum;
            Task task = Task.builder()
                    .id(taskId)
                    .status(status)
                    .build();
            taskManager.updateTaskStatus(task);
            req.getSession().setAttribute("msg", "task was updated");
            resp.sendRedirect("/home");
        }

    }

}

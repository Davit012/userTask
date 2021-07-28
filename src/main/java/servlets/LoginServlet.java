package servlets;

import managers.UserManager;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.getUserByEmailAndPassword(email, password);
        if (user == null) {
            req.getSession().setAttribute("msg", "Wrong username or password");
            resp.sendRedirect("/");
        } else {
            if (user.getType().name().equalsIgnoreCase("Manager")) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/adm");
            } else {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/home");
            }
        }

    }
}
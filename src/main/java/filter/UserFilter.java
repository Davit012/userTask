package filter;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/adm","/addUser","/addTask","/deleteUser","/more"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user =(User) req.getSession().getAttribute("user");
            if (user.getType().name().equalsIgnoreCase("Manager")){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                req.setAttribute("msg","this page available only for managers");
                resp.sendRedirect("/home");
            }
    }

    @Override
    public void destroy() {

    }
}

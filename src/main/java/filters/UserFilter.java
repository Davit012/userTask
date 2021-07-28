package filters;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/addTask","/adduser","/adm","/deleteUser","/updateStatus","/more" })
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user =(User) req.getSession().getAttribute("user");
        String userType = user.getType().name();


        if (userType.equalsIgnoreCase("manager")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}

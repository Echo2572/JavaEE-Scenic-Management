package control;

import biz.UserLoginEJB;
import entity.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserLoginEJB userLoginEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userLoginEJB.login(username, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("allScenic");
        } else {
            response.sendRedirect("Login.jsp?error=1&reset=0");
        }
    }
}

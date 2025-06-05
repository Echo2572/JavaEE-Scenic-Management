package control;

import biz.UserRegistrationEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserRegistrationEJB userRegistrationEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        boolean registrationStatus = userRegistrationEJB.registerUser(userName, password, email);

        if (registrationStatus) {
            response.sendRedirect("RegisterSuccess.jsp");
        } else {
            response.sendRedirect("RegisterFail.jsp?error=UsernameAlreadyExists");
        }
    }
}



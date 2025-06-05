package control;

import biz.EditUserInfoEJB;
import entity.User;
import util.SimpleEncryption;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private EditUserInfoEJB editUserInfoEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String encryptPassword=SimpleEncryption.encrypt(password);

        boolean updateSuccess = editUserInfoEJB.updateUserInfo(username, email, encryptPassword);

        if (updateSuccess) {
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("user");
            if (sessionUser != null) {      	
                sessionUser.setEmail(email);
                sessionUser.setPassword(encryptPassword);
                session.setAttribute("user", sessionUser);
            }

            response.sendRedirect("userInfo.jsp");
        } else {
            response.sendRedirect("editUserInfo.jsp?error=1");
        }
    }
}

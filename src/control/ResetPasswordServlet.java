package control;

import biz.ResetPasswordEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ResetPasswordEJB resetPasswordEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("resetPassword.jsp?error=1&token=" + token);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/scenicplatform";
        String user = "root";
        String pass = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            // Validate the token
            PreparedStatement checkTokenStatement = con.prepareStatement("SELECT email FROM password_reset_tokens WHERE token = ?");
            checkTokenStatement.setString(1, token);
            ResultSet rs = checkTokenStatement.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");

                // Update the user's password using JPA
                resetPasswordEJB.updateUserPassword(email, newPassword);

                // Delete the used token
                PreparedStatement deleteTokenStatement = con.prepareStatement("DELETE FROM password_reset_tokens WHERE token = ?");
                deleteTokenStatement.setString(1, token);
                deleteTokenStatement.executeUpdate();

                response.sendRedirect("Login.jsp?reset=1&error=0");
                deleteTokenStatement.close();
            } else {
                // Invalid or expired token
                response.sendRedirect("resetPassword.jsp?error=2&token=" + token);
            }

            checkTokenStatement.close();
            con.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }catch(SQLException e2){
        	e2.printStackTrace();
        }
    }
}

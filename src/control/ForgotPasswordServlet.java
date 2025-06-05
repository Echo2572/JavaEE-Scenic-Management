package control;

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
import java.util.UUID;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        

        String url = "jdbc:mysql://localhost:3306/scenicplatform";
        String user = "root";
        String pass = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            // Check if the email exists
            PreparedStatement checkEmailStatement = con.prepareStatement("SELECT username FROM users WHERE email = ?");
            checkEmailStatement.setString(1, email);
            ResultSet rs = checkEmailStatement.executeQuery();

            if (rs.next()) {
                // Email exists, generate reset token
                String token = UUID.randomUUID().toString();
                PreparedStatement insertTokenStatement = con.prepareStatement("INSERT INTO password_reset_tokens(email, token) VALUES (?, ?)");
                insertTokenStatement.setString(1, email);
                insertTokenStatement.setString(2, token);
                insertTokenStatement.executeUpdate();

                response.sendRedirect("resetPassword.jsp?token=" + token+"&error=0");
                insertTokenStatement.close();
            } else {
                // Email does not exist
                response.sendRedirect("forgotPassword.jsp?error=1");
            }

            checkEmailStatement.close();
            con.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }catch(SQLException e2){
        	e2.printStackTrace();
        }
    }
}

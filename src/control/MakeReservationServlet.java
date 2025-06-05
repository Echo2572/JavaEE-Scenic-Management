package control;

import biz.ReservationBean;
import entity.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/MakeReservationServlet")
public class MakeReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ReservationBean reservationBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int scenicId = Integer.parseInt(request.getParameter("scenicId"));
        String visitTimeStr = request.getParameter("visitTime");

        // Convert the input from datetime-local to the required Timestamp format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(visitTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp visitTime = new Timestamp(parsedDate.getTime());

        boolean success = reservationBean.makeReservation(userId, scenicId, visitTime);

        if (success) {
//        	response.sendRedirect("ScenicDetail?id=" + scenicId);
        	response.sendRedirect("ScenicDetail?id=" + scenicId + "&reservationSuccess=true");
        }
    }
}

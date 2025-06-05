package control;

import biz.QueueInfoEJB;
import entity.Reservation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/QueueInfoServlet")
public class QueueInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private QueueInfoEJB queueInfoEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scenicId = Integer.parseInt(request.getParameter("scenicId"));

        List<Reservation> reservations = queueInfoEJB.getReservationsByScenicId(scenicId);

        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("queueInfo.jsp").forward(request, response);
    }
}

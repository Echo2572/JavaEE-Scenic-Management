package control;

import client.TicketCheckJMSProducer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CheckTicketServlet")
public class CheckTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketCode = request.getParameter("ticketCode");

        TicketCheckJMSProducer producer = new TicketCheckJMSProducer();
        try {
            producer.sendMessage(ticketCode);
            response.sendRedirect("showReservation?checkSuccess=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("showReservation?checkSuccess=false");
        }
    }
}

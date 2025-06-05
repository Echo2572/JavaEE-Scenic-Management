//package control;
//
//import biz.LookUPReservationEJB;
//import entity.Scenic;
//import entity.User;
//
//import javax.ejb.EJB;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/showReservation")
//public class showReservationServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @EJB
//    private LookUPReservationEJB lookUPReservationEJB;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // ��session�л�ȡ��ǰ�û�
//        HttpSession session = request.getSession();
//        User currentUser = (User) session.getAttribute("user");
//
//        // ��ȡ��ǰ�û������о�����Ϣ
//        List<Scenic> scenicList = lookUPReservationEJB.getAllScenicByUser(currentUser);
//
//        // ��������Ϣ����request��
//        request.setAttribute("scenicList", scenicList);
//
//        // ת����selfReservation.jspҳ��
//        RequestDispatcher dispatcher = request.getRequestDispatcher("selfReservation.jsp");
//        dispatcher.forward(request, response);
//    }
//}








package control;

import biz.LookUPReservationEJB;
import entity.Reservation;
import entity.User;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/showReservation")
public class showReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private LookUPReservationEJB lookUPReservationEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ��session�л�ȡ��ǰ�û�
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // ��ȡ��ǰ�û�������ԤԼ��Ϣ
        List<Reservation> reservationList = lookUPReservationEJB.getAllReservationsByUser(currentUser);

        // ��ԤԼ��Ϣ����request��
        request.setAttribute("reservationList", reservationList);
        
        String checkSuccess = request.getParameter("checkSuccess");
        request.setAttribute("checkSuccess", checkSuccess);

        // ת����selfReservation.jspҳ��
        RequestDispatcher dispatcher = request.getRequestDispatcher("selfReservation.jsp");
        dispatcher.forward(request, response);
    }
}

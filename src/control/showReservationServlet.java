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
//        // 从session中获取当前用户
//        HttpSession session = request.getSession();
//        User currentUser = (User) session.getAttribute("user");
//
//        // 获取当前用户的所有景区信息
//        List<Scenic> scenicList = lookUPReservationEJB.getAllScenicByUser(currentUser);
//
//        // 将景区信息存入request中
//        request.setAttribute("scenicList", scenicList);
//
//        // 转发到selfReservation.jsp页面
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
        // 从session中获取当前用户
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // 获取当前用户的所有预约信息
        List<Reservation> reservationList = lookUPReservationEJB.getAllReservationsByUser(currentUser);

        // 将预约信息存入request中
        request.setAttribute("reservationList", reservationList);
        
        String checkSuccess = request.getParameter("checkSuccess");
        request.setAttribute("checkSuccess", checkSuccess);

        // 转发到selfReservation.jsp页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("selfReservation.jsp");
        dispatcher.forward(request, response);
    }
}

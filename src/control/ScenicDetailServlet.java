package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.ejb.EJB;

import biz.ScenicDetailEJB;
import entity.Scenic;

@WebServlet("/ScenicDetail")
public class ScenicDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ScenicDetailEJB scenicDetailEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Scenic scenic = scenicDetailEJB.getScenicById(id);

        request.setAttribute("scenic", scenic);
        request.getRequestDispatcher("scenicDetail.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

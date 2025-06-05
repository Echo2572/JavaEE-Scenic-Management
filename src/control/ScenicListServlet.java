package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;

import biz.AllScenicEJB;
import entity.Scenic;

@WebServlet("/allScenic")
public class ScenicListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private AllScenicEJB allScenicEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Scenic> scenicList = allScenicEJB.getAllScenic();

        request.setAttribute("scenicList", scenicList);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
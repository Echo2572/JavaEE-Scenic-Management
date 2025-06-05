package control;

import entity.ServiceInfo;
import biz.ServiceInfoEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ServiceInfoServlet")
public class ServiceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private ServiceInfoEJB serviceInfoEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scenicId = Integer.parseInt(request.getParameter("scenicId"));
        List<ServiceInfo> serviceInfos = serviceInfoEJB.getServiceInfo(scenicId);

        request.setAttribute("serviceInfos", serviceInfos);
        request.getRequestDispatcher("serviceInfo.jsp").forward(request, response);
    }
}

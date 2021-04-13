package servlets;

import service.TourService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TravelAgencyServlet extends HttpServlet {
    private final TourService tourService = new TourService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            tourService.getTours(request);
            request.getRequestDispatcher("pages/TravelAgency.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}
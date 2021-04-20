package servlets;

import model.tour.Tour;
import model.user.User;
import service.TourService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private final TourService tourService = new TourService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
            if (tourService.getTourById(request)) {
                request.getRequestDispatcher("pages/Order.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("pages/TravelAgency.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("logout");
        }
    }
}
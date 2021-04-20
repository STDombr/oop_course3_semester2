package servlets;

import model.user.User;
import service.TourService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private final TourService tourService = new TourService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
            User admin = (User) request.getSession().getAttribute("user");
            if (admin.isAdmin()) {
                if (tourService.getTourById(request)) {
                    if (request.getParameter("id") != null) {
                        System.out.println("ffff");
                    }
                    request.getRequestDispatcher("pages/Admin.jsp").forward(request, response);
                }
            }
                request.getRequestDispatcher("pages/TravelAgency.jsp").forward(request, response);
                //error page
        } else {
            response.sendRedirect("logout");
        }
    }
}
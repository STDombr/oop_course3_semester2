package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("nickname") != null) {
            if (userService.registerUser(request)) {
                response.sendRedirect("home");
            } else {
                request.getRequestDispatcher("pages/Registration.jsp").forward(request, response);
            }
        } else {
            if (request.getSession().getAttribute("user") != null) {
                response.sendRedirect("home");
            } else {
                request.getSession().removeAttribute("registrationErrorMessage");
                request.getRequestDispatcher("pages/Registration.jsp").forward(request, response);
            }
        }
    }
}
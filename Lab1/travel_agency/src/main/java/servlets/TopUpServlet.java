package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopUpServlet extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("topUpErrorMessage");

        if (request.getSession().getAttribute("user") != null) {
            if (userService.topUp(request)) {
                response.sendRedirect("list");
            } else {
                request.getRequestDispatcher("pages/TopUp.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("logout");
        }
    }
}
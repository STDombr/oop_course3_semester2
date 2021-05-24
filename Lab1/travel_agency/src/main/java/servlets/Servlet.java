package servlets;

import command.CommandContainer;
import service.TourService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getRequestURI();
        String command = CommandContainer.get(commandName).execute(request);

        if (command.contains("redirect:")) {
            response.sendRedirect("/travel_agency" + command.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(command).forward(request, response);
        }
    }
}
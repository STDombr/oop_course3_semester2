package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand extends Command {
    private final UserService userService = new UserService();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Registration command");

        request.getSession().removeAttribute("registrationErrorMessage");

        if (request.getParameter("nickname") != null) {
            if (userService.registerUser(request)) {
                logger.info("New user: " + request.getSession().getAttribute("user"));
                return "redirect:/list";
            } else {
                logger.error("This user is registered");
                return "pages/registration.jsp";
            }
        } else {
            if (request.getSession().getAttribute("user") != null) {
                return "redirect:/list";
            } else {
                return "pages/registration.jsp";
            }
        }
    }
}

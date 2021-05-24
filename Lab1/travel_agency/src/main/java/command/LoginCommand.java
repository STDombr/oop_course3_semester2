package command;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import service.UserService;

public class LoginCommand extends Command {
    private final UserService userService = new UserService();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Login command");

        request.getSession().removeAttribute("loginErrorMessage");

        if (request.getParameter("nickname") != null) {
            if (userService.loginUser(request)) {
                logger.info("Current user: " + request.getSession().getAttribute("user"));
                return "redirect:/list";
            } else {
                logger.error("Invalid 'nickname' or 'password'!");
                return "pages/login.jsp";
            }
        } else {
            if (request.getSession().getAttribute("user") != null) {
                return "redirect:/list";
            } else {
                return "pages/login.jsp";
            }
        }
    }
}

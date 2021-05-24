package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class TopUpCommand extends Command {
    private final UserService userService = new UserService();
    private static final Logger logger = LogManager.getLogger(TopUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Top Up command");

        if (request.getSession().getAttribute("user") != null) {
            if (userService.topUp(request)) {
                return "redirect:/list";
            } else {
                logger.error("Balance is not changed");
                return "pages/topUp.jsp";
            }
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

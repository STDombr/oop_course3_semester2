package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BalanceCommand extends Command {
    private final UserService userService = new UserService();
    private static final Logger logger = LogManager.getLogger(BalanceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Balance command");

        request.getSession().removeAttribute("topUpErrorMessage");

        if (request.getSession().getAttribute("user") != null) {
            return "pages/topUp.jsp";
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

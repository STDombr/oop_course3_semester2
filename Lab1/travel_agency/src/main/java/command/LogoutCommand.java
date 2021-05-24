package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Logout command");

        HttpSession session=request.getSession();
        session.invalidate();

        return "redirect:/login";
    }
}

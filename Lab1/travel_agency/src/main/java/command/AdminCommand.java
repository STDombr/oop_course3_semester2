package command;

import model.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand extends Command {
    private final TourService tourService = new TourService();
    private static final Logger logger = LogManager.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Admin command");

        if (request.getSession().getAttribute("user") != null) {
            User admin = (User) request.getSession().getAttribute("user");
            if (admin.isAdmin()) {
                if (tourService.getTourById(request)) {
                    return "pages/admin.jsp";
                } else {
                    logger.error("Tour id is invalid");
                    return "redirect:/list";
                }
            } else {
                logger.error("User is not admin");
                return "redirect:/list";
            }
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

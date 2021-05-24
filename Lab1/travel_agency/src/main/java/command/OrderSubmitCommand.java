package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;

import javax.servlet.http.HttpServletRequest;

public class OrderSubmitCommand extends Command {
    private final TourService tourService = new TourService();
    private static final Logger logger = LogManager.getLogger(OrderSubmitCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Submit Order command");

        if (request.getSession().getAttribute("user") != null) {
            if (!tourService.newOrder(request)) {
                return "redirect:/order";
            } else {
                logger.error("Tour id is invalid");
                return "redirect:/list";
            }
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

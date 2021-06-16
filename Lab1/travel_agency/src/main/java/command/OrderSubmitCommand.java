package command;

import model.tour.Tour;
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
                return "redirect:/order?id=" + ((Tour) request.getSession().getAttribute("tour")).getId();
            } else {
                logger.error("Order is created");
                return "redirect:/list";
            }
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

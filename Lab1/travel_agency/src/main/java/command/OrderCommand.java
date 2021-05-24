package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;

import javax.servlet.http.HttpServletRequest;

public class OrderCommand extends Command {
    private final TourService tourService = new TourService();
    private static final Logger logger = LogManager.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing Order command");

        if (request.getSession().getAttribute("user") != null) {
            if (tourService.getTourById(request)) {
                return "pages/order.jsp";
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

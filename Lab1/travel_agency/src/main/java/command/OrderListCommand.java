package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;

import javax.servlet.http.HttpServletRequest;

public class OrderListCommand extends Command {
    private final TourService tourService = new TourService();
    private static final Logger logger = LogManager.getLogger(OrderListCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Executing OrderList command");

        if (request.getSession().getAttribute("user") != null) {
            tourService.getOrderByUserId(request);

            return "pages/orderList.jsp";
        } else {
            logger.error("User is not authorized");
            return "redirect:/logout";
        }
    }
}

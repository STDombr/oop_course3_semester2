package service;

import dao.FactoryDAO;
import dao.country.CountryDAO;
import dao.order.OrderDAO;
import dao.tour.TourDAO;
import dao.tour.TourTypeDAO;
import dao.user.UserDAO;
import model.country.Country;
import model.order.Order;
import model.tour.Tour;
import model.tour.TourBuilder;
import model.tour.TourType;
import model.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TourService {
    private final TourDAO tourDAO;
    private final TourTypeDAO tourTypeDAO;
    private final CountryDAO countryDAO;
    private final OrderDAO orderDAO;
    private final UserDAO userDAO;
    private final static Logger logger = LogManager.getLogger(TourService.class);

    public TourService() {
        this.tourDAO = FactoryDAO.createTourDao();
        this.tourTypeDAO = FactoryDAO.createTourTypeDao();
        this.countryDAO = FactoryDAO.createCountryDao();
        this.orderDAO = FactoryDAO.createOrderDao();
        this.userDAO = FactoryDAO.createUserDao();
    }

    public void getTours(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<TourType> tourTypes = tourTypeDAO.getAllTourTypes();
        List<Country> countries = countryDAO.getAllCountries();

        session.removeAttribute("tours");
        session.setAttribute("tours", tourDAO.getAllTours(tourTypes, countries));
    }

    public boolean getTourById(HttpServletRequest request) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));

        if (id > 0) {
            List<TourType> tourTypes = tourTypeDAO.getAllTourTypes();
            List<Country> countries = countryDAO.getAllCountries();

            HttpSession session = request.getSession();

            Tour tour = tourDAO.getTourById(id, tourTypes, countries);
            if (tour.getId() == id) {
                List<Order> orders = orderDAO.getOrderByTourId(tour.getId());
                int count = 0;
                for (int i = 0; i < orders.size(); i++) {
                    try {
                        Date date = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(orders.get(i).getDate());

                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -7);

                        if (!date.after(cal.getTime())) {
                            count++;
                        }
                    } catch (ParseException e) {
                        logger.warn("Cound not parse order date: {}", e.getMessage());
                    }
                }
                session.removeAttribute("ordersCount");
                session.removeAttribute("tour");
                session.setAttribute("ordersCount", count);
                session.setAttribute("tour", tour);

                return true;
            }
        }
        return false;
    }

    public boolean updateTour(HttpServletRequest request) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));

        if (id > 0) {
            Tour tour = new TourBuilder()
                    .setId(id)
                    .setName(request.getParameter("name"))
                    .setPrice(Float.parseFloat(request.getParameter("price")))
                    .setDays(Integer.parseInt(request.getParameter("days")))
                    .setInfo(request.getParameter("info"))
                    .setSale(Integer.parseInt(request.getParameter("sale")))
                    .build();

            tourDAO.updateTourById(id, tour);
            return true;
        } else {
            return false;
        }
    }

    public boolean newOrder(HttpServletRequest request) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));

        if (id > 0) {

            User user = (User) request.getSession().getAttribute("user");
            int count = Integer.parseInt(request.getParameter("count"));
            Tour tour = (Tour) request.getSession().getAttribute("tour");

            if (user.getMoney() >= (count * tour.getPrice() * (100 - tour.getSale())) / 100) {
                orderDAO.insertOrder(id, user.getId(), count);
                userDAO.addToMoney(user, (float) ((-1) * (count * tour.getPrice() * (100 - tour.getSale())) / 100));
                return true;
            }
            logger.error("User don't have enough money");
        }

        return false;
    }

    public void getOrderByUserId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        List<Order> orders = orderDAO.getOrderByUserId(user.getId());
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setName(tourDAO.getTourById(orders.get(i).getTourId(), null, null).getName());
        }

        HttpSession session = request.getSession();
        session.setAttribute("orders", orders);
    }
}

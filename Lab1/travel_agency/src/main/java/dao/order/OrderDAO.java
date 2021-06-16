package dao.order;

import dao.Converter;
import dao.country.CountryDAO;
import model.country.Country;
import model.order.Order;
import model.tour.Tour;
import model.tour.TourType;
import model.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OrderDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private final static Logger logger = LogManager.getLogger(OrderDAO.class);

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertOrder(int tourId, int userId, int count) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("orders.create"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, tourId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, count);
            preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            preparedStatement.executeUpdate();
            logger.info("Order successfully created");
        } catch (SQLException throwables) {
            logger.warn("Order inserting error: {}", throwables.getMessage());
        }
        return true;
    }

    public List<Order> getOrderByUserId(int userId) {
        List<Order> list = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("orders.get_orders_by_user_id"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Order order = Converter.getOrderFromResultSet(result);
                list.add(order);
            }
        } catch (SQLException throwables) {
            logger.warn("Could not find orders: {}", throwables.getMessage());
        }
        logger.info("Orders successfully found");
        return list;
    }

    public List<Order> getOrderByTourId(int tourId) {
        List<Order> list = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("orders.get_orders_by_tour_id"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, tourId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Order order = Converter.getOrderFromResultSet(result);
                list.add(order);
            }
        } catch (SQLException throwables) {
            logger.warn("Could not find orders: {}", throwables.getMessage());
        }
        logger.info("Orders successfully found");
        return list;
    }
}

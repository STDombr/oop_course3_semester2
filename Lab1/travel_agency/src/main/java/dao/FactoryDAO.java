package dao;

import dao.country.CountryDAO;
import dao.tour.TourDAO;
import dao.tour.TourTypeDAO;
import dao.user.UserDAO;

public class FactoryDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static UserDAO createUserDao() {
        return new UserDAO(connectionPool.getConnection());
    }

    public static TourDAO createTourDao() {
        return new TourDAO(connectionPool.getConnection());
    }

    public static TourTypeDAO createTourTypeDao() {
        return new TourTypeDAO(connectionPool.getConnection());
    }

    public static CountryDAO createCountryDao() {
        return new CountryDAO(connectionPool.getConnection());
    }
}

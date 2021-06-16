package dao.tour;

import dao.Converter;
import dao.user.UserDAO;
import model.country.Country;
import model.tour.Tour;
import model.tour.TourType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TourDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private final static Logger logger = LogManager.getLogger(TourDAO.class);

    public TourDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Tour> getAllTours(List<TourType> tourTypes, List<Country> countries) {
        List<Tour> list = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("tours.get_tours"), Statement.RETURN_GENERATED_KEYS);
            String tourTypeName = "";
            String countryName = "";

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                for (TourType tourType : tourTypes) {
                    if (tourType.getId() == result.getInt("tour_type")) {
                        tourTypeName = tourType.getName();
                    }
                }

                for (Country country : countries) {
                    if (country.getId() == result.getInt("country")) {
                        countryName = country.getName();
                    }
                }

                Tour tour = Converter.getTourFromResultSet(result);
                tour.setCountry(countryName);
                tour.setTourType(tourTypeName);

                list.add(tour);
            }
        } catch (SQLException throwables) {
            logger.warn("Could not find tours: {}", throwables.getMessage());
        }
        list.sort(Comparator.comparing(Tour::getId));
        logger.info("Tours successfully found");
        return list;
    }

    public void updateTourById(int id, Tour tour) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("tours.update_tour"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, tour.getName());
            preparedStatement.setFloat(2, tour.getPrice());
            preparedStatement.setInt(3, tour.getDays());
            preparedStatement.setString(4, tour.getInfo());
            preparedStatement.setInt(5, tour.getSale());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
            logger.info("Tour successfully updated");

        } catch (SQLException throwables) {
            logger.warn("Update tour error: {}", throwables.getMessage());
        }
    }

    public Tour getTourById(int id, List<TourType> tourTypes, List<Country> countries) {
        Tour tour = new Tour();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("tours.get_tour_by_id"), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            String tourTypeName = "";
            String countryName = "";

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                if (tourTypes != null) {
                    for (TourType tourType : tourTypes) {
                        if (tourType.getId() == result.getInt("tour_type")) {
                            tourTypeName = tourType.getName();
                        }
                    }
                }

                if (countries != null) {
                    for (Country country : countries) {
                        if (country.getId() == result.getInt("country")) {
                            countryName = country.getName();
                        }
                    }
                }

                tour = Converter.getTourFromResultSet(result);
                tour.setCountry(countryName);
                tour.setTourType(tourTypeName);
            }
        } catch (SQLException throwables) {
            logger.warn("Could not find tour by id: {}", throwables.getMessage());
        }

        logger.info("Tour successfully found");
        return tour;
    }

}

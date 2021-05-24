package dao.tour;

import dao.Converter;
import dao.user.UserDAO;
import model.tour.TourType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TourTypeDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private final static Logger logger = LogManager.getLogger(TourTypeDAO.class);

    public TourTypeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TourType> getAllTourTypes() {
        List<TourType> list = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("tour_types.get_countries"), Statement.RETURN_GENERATED_KEYS);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                TourType tourType = Converter.getTourTypeFromResultSet(result);
                list.add(tourType);
            }
        } catch (SQLException throwables) {
            logger.warn("Could not find tour types: {}", throwables.getMessage());
        }
        logger.info("Tour types successfully found");
        return list;
    }

}

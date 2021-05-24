package dao.country;

import dao.Converter;
import dao.user.UserDAO;
import model.country.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CountryDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private final static Logger logger = LogManager.getLogger(CountryDAO.class);

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getAllCountries() {
        List<Country> list = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("countries.get_countries"), Statement.RETURN_GENERATED_KEYS);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Country country = Converter.getCountryFromResultSet(result);
                list.add(country);
            }
        } catch (SQLException throwables) {
            logger.warn("Cound not find countries: {}", throwables.getMessage());
        }
        logger.info("Countries successfully found");
        return list;
    }

}

package dao.tour;

import dao.Converter;
import model.country.Country;
import model.tour.Tour;
import model.tour.TourType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TourDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");

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
            throwables.printStackTrace();
        }
        return list;
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

                tour = Converter.getTourFromResultSet(result);
                tour.setCountry(countryName);
                tour.setTourType(tourTypeName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tour;
    }

}

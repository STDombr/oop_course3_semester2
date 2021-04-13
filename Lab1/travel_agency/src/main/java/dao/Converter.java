package dao;

import model.country.Country;
import model.country.CountryBuilder;
import model.tour.Tour;
import model.tour.TourBuilder;
import model.tour.TourType;
import model.tour.TourTypeBuilder;
import model.user.User;
import model.user.UserBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {
    public static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getInt("id"))
                .setNickname(resultSet.getString("nickname"))
                .setPassword(resultSet.getString("password"))
                .setAdmin(resultSet.getBoolean("admin"))
                .setMoney(resultSet.getFloat("money"))
                .setTransactions(resultSet.getInt("transactions"))
                .build();
    }

    public static Tour getTourFromResultSet(ResultSet resultSet) throws SQLException {
        return new TourBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setPrice(resultSet.getFloat("price"))
                .setDays(resultSet.getInt("days"))
                .setInfo(resultSet.getString("info"))
                .setSale(resultSet.getBoolean("sale"))
                .build();
    }

    public static Country getCountryFromResultSet(ResultSet resultSet) throws SQLException {
        return new CountryBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .build();
    }

    public static TourType getTourTypeFromResultSet(ResultSet resultSet) throws SQLException {
        return new TourTypeBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .build();
    }
}

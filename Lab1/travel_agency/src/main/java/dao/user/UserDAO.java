package dao.user;

import dao.Converter;
import model.user.User;

import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO {
    private final Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("sql");
    private final static Logger logger = LogManager.getLogger(UserDAO.class);

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertUser(User user) {
        try {
            if (findUserByNickname(user.getNickname()).isPresent()) {
                return false;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("users.create"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isAdmin());
            preparedStatement.setFloat(4, user.getMoney());
            preparedStatement.setInt(5, user.getTransactions());
            preparedStatement.executeUpdate();
            logger.info("User successfully created");
        } catch (SQLException throwables) {
            logger.warn("User inserting error: {}", throwables.getMessage());
        }
        return true;
    }

    public Optional<User> findUserByNickname(String nickname) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("users.find_by_nickname"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, nickname);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                logger.info("User successfully found");
                return Optional.of(Converter.getUserFromResultSet(result));
            }
        } catch (SQLException throwables) {
            logger.warn("Error with finding user: {}", throwables.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Float> getMoney(String nickname) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("users.get_money"), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, nickname);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                logger.info("Money successfully received");
                return Optional.of(Converter.getMoneyFromResultSet(result));
            }
        } catch (SQLException throwables) {
            logger.warn("Error with getting money: {}", throwables.getMessage());
        }
        return Optional.empty();
    }

    public boolean addToMoney(User user, Float popUpMoney) {
        float money = getMoney(user.getNickname()).orElseThrow(() -> new RuntimeException("Cant find user money!"));

        money += popUpMoney;

        if (money >= 0) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("users.update_money"), Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setFloat(1, money);
                preparedStatement.setString(2, user.getNickname());
                preparedStatement.executeUpdate();

                user.setMoney(money);
                logger.info("Money successfully changed");
                return true;
            } catch (SQLException throwables) {
                logger.warn("Changing money error: {}", throwables.getMessage());
            }
        }
        return false;
    }
}

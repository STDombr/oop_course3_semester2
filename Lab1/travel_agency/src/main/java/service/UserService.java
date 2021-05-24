package service;

import dao.FactoryDAO;
import dao.user.UserDAO;
import model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = FactoryDAO.createUserDao();
    }

    public boolean loginUser(HttpServletRequest request) {
        User user = userDAO.findUserByNickname(request.getParameter("nickname")).orElse(new User());

        HttpSession session = request.getSession();
        if (user.getPassword().equals(request.getParameter("password"))) {
            session.setAttribute("user", user);
            return true;
        } else {
            session.setAttribute("loginErrorMessage", "Error: Invalid 'nickname' or 'password'!");
            return false;
        }
    }

    public boolean registerUser(HttpServletRequest request) {
        User user = new User();
        user.setNickname(request.getParameter("nickname"));
        user.setPassword(request.getParameter("password"));

        HttpSession session = request.getSession();
        if (userDAO.insertUser(user)) {
            session.setAttribute("user", user);
            return true;
        } else {
            session.setAttribute("registrationErrorMessage", "Error: This 'nickname' is registered!");
            return false;
        }
    }

    public boolean topUp(HttpServletRequest request) {
        if (request.getParameter("money") == null) {
            return false;
        }

        User user = (User) request.getSession().getAttribute("user");

        if (userDAO.addToMoney(user, Float.parseFloat(request.getParameter("money")))) {
            return true;
        } else {
            request.getSession().setAttribute("topUpErrorMessage", "Error: Balance is not changed!");
            return false;
        }
    }
}

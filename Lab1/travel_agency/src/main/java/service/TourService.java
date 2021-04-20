package service;

import dao.FactoryDAO;
import dao.country.CountryDAO;
import dao.tour.TourDAO;
import dao.tour.TourTypeDAO;
import model.country.Country;
import model.tour.Tour;
import model.tour.TourType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TourService {
    private final TourDAO tourDAO;
    private final TourTypeDAO tourTypeDAO;
    private final CountryDAO countryDAO;

    public TourService() {
        this.tourDAO = FactoryDAO.createTourDao();
        this.tourTypeDAO = FactoryDAO.createTourTypeDao();
        this.countryDAO = FactoryDAO.createCountryDao();
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

        if (id != 0) {
            List<TourType> tourTypes = tourTypeDAO.getAllTourTypes();
            List<Country> countries = countryDAO.getAllCountries();

            HttpSession session = request.getSession();

            Tour tour = tourDAO.getTourById(id, tourTypes, countries);
            if (tour.getId() == id) {
                session.removeAttribute("tour");
                session.setAttribute("tour", tour);

                return true;
            }
        }
        return false;
    }
}
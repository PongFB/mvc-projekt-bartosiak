package pl.merito.salon.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.merito.salon.model.Brand;
import pl.merito.salon.model.Car;
import pl.merito.salon.model.CarValidator;
import pl.merito.salon.repository.BrandRepository;
import pl.merito.salon.repository.CarRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = {"/cars", "/cars/details", "/cars/add"})
public class CarController extends HttpServlet {

    private CarRepository cars;
    private BrandRepository brands;

    @Override
    public void init() {
        cars = (CarRepository) getServletContext().getAttribute("carRepository");
        brands = (BrandRepository) getServletContext().getAttribute("brandRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/cars" -> list(req, resp);
            case "/cars/details" -> details(req, resp);
            case "/cars/add" -> showForm(req, resp);
            default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("/cars/add".equals(req.getServletPath())) {
            add(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cars", cars.findAll());
        forward("cars/list", req, resp);
    }

    private void details(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Optional<Car> car = parseId(req.getParameter("id")).flatMap(cars::findById);
        if (car.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Nie znaleziono samochodu o podanym ID.");
            return;
        }
        req.setAttribute("car", car.get());
        forward("cars/details", req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("brands", brands.findAll());
        forward("cars/form", req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String brandIdParam = req.getParameter("brandId");
        String modelParam = req.getParameter("model");
        String yearParam = req.getParameter("year");
        String priceParam = req.getParameter("price");

        Map<String, String> errors = CarValidator.validate(brandIdParam, modelParam, yearParam, priceParam);

        Optional<Brand> brand = Optional.empty();
        if (!errors.containsKey("brandId")) {
            brand = parseId(brandIdParam).flatMap(brands::findById);
            if (brand.isEmpty()) {
                errors.put("brandId", "Wybrana marka nie istnieje.");
            }
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            showForm(req, resp);
            return;
        }

        Car savedCar = cars.save(new Car(
                brand.get(),
                modelParam.trim(),
                Integer.parseInt(yearParam.trim()),
                new BigDecimal(priceParam.trim().replace(",", "."))
        ));

        HttpSession session = req.getSession();
        Integer added = (Integer) session.getAttribute("addedCount");
        session.setAttribute("addedCount", added == null ? 1 : added + 1);
        session.setAttribute("lastCar", savedCar.getBrand().getName() + " " + savedCar.getModel());

        resp.sendRedirect(req.getContextPath() + "/cars");
    }

    private Optional<Long> parseId(String value) {
        if (value == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Long.valueOf(value.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private void forward(String view, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(req, resp);
    }
}
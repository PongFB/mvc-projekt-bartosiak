package pl.merito.salon.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cars/add")
public class CarFormServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/cars/form.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, String> errors = validate(req);

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }

        String brand = req.getParameter("brand").trim();
        String model = req.getParameter("model").trim();
        String year = req.getParameter("year").trim();
        String price = req.getParameter("price").trim();

        HttpSession session = req.getSession();
        Integer added = (Integer) session.getAttribute("addedCount");
        session.setAttribute("addedCount", added == null ? 1 : added + 1);
        session.setAttribute("lastCar", brand + " " + model + " (" + year + ", " + price + " PLN)");

        resp.sendRedirect(req.getContextPath() + "/cars/added");
    }

    private Map<String, String> validate(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String yearParam = req.getParameter("year");
        String priceParam = req.getParameter("price");

        if (brand == null || brand.isBlank()) {
            errors.put("brand", "Marka samochodu jest wymagana.");
        }
        if (model == null || model.isBlank()) {
            errors.put("model", "Model samochodu jest wymagany.");
        }

        try {
            int year = Integer.parseInt(yearParam == null ? "" : yearParam.trim());
            int currentYear = Year.now().getValue();
            if (year < 1900 || year > currentYear) {
                errors.put("year", "Rok produkcji musi mieścić się w zakresie 1900–" + currentYear + ".");
            }
        } catch (NumberFormatException e) {
            errors.put("year", "Rok produkcji musi być liczbą całkowitą.");
        }

        try {
            double price = Double.parseDouble(priceParam == null ? "" : priceParam.trim().replace(",", "."));
            if (price <= 0) {
                errors.put("price", "Cena samochodu musi być większa od zera.");
            }
        } catch (NumberFormatException e) {
            errors.put("price", "Cena samochodu musi być poprawną liczbą.");
        }

        return errors;
    }
}
package pl.merito.salon.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cars/add")
public class CarFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        renderForm(req, resp, new ArrayList<>(), "", "", "", "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        String brand = trim(req.getParameter("brand"));
        String model = trim(req.getParameter("model"));
        String year = trim(req.getParameter("year"));
        String price = trim(req.getParameter("price"));

        List<String> errors = new ArrayList<>();

        if (brand.isEmpty()) {
            errors.add("Brak marki samochodu.");
        }
        if (model.isEmpty()) {
            errors.add("Brak modelu samochodu.");
        }

        try {
            int y = Integer.parseInt(year);
            int currentYear = Year.now().getValue();
            if (y < 1885 || y > currentYear) {
                errors.add("Rok produkcji musi mieścić się w zakresie 1885–" + currentYear + ".");
            }
        } catch (NumberFormatException e) {
            errors.add("Rok produkcji musi być liczbą całkowitą.");
        }

        try {
            double p = Double.parseDouble(price.replace(",", "."));
            if (p <= 0) {
                errors.add("Cena samochodu musi być większa od zera.");
            }
        } catch (NumberFormatException e) {
            errors.add("Cena samochodu musi być poprawną liczbą.");
        }

        if (!errors.isEmpty()) {
            renderForm(req, resp, errors, brand, model, year, price);
            return;
        }

        String fullCarName = brand + " " + model + " (" + year + ")";
        String encoded = URLEncoder.encode(fullCarName, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/cars/added?car=" + encoded);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp, List<String> errors,
                            String brand, String model, String year, String price) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Dodaj samochód</title></head><body>");
        out.println("<h1>Dodaj samochód do oferty salonu</h1>");

        if (!errors.isEmpty()) {
            out.println("<div style='color:red; border:1px solid red; padding:10px; margin-bottom:15px;'>");
            out.println("<strong>Formularz zawiera błędy:</strong><ul>");
            for (String error : errors) {
                out.println("<li>" + error + "</li>");
            }
            out.println("</ul></div>");
        }

        out.println("<form method='post'>");
        out.println("<p><label>Marka: <input type='text' name='brand' value='" + brand + "' placeholder='np. Ferrari'></label></p>");
        out.println("<p><label>Model: <input type='text' name='model' value='" + model + "' placeholder='np. 296 GTB'></label></p>");
        out.println("<p><label>Rok produkcji: <input type='text' name='year' value='" + year + "' placeholder='np. 2024'></label></p>");
        out.println("<p><label>Cena (PLN): <input type='text' name='price' value='" + price + "' placeholder='np. 1450000'></label></p>");
        out.println("<button type='submit'>Zapisz samochód</button>");
        out.println("</form>");
        out.println("<p><a href='" + req.getContextPath() + "/'>Powrót na stronę główną</a></p>");
        out.println("</body></html>");
    }
}
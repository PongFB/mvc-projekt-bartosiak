package pl.merito.salon.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cars/added")
public class CarAddedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String car = req.getParameter("car");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Zapisano samochód</title></head><body>");
        out.println("<h1>Zapisano samochód w ofercie: " + (car != null ? car : "") + "</h1>");
        out.println("<p><a href='" + req.getContextPath() + "/cars/add'>Dodaj kolejny samochód</a></p>");
        out.println("<p><a href='" + req.getContextPath() + "/'>Powrót na stronę główną</a></p>");
        out.println("</body></html>");
    }
}
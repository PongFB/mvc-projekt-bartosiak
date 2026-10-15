package pl.merito.salon.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Salon Samochodowy</title></head><body>");
        out.println("<h1>Witaj w naszym Salonie Samochodowym!</h1>");
        out.println("<p><a href='index.html'>Powrót na stronę główną</a></p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        String clientName = req.getParameter("clientName");
        String carModel = req.getParameter("carModel");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'><title>Potwierdzenie zapytania</title></head><body>");
        out.println("<h1>Cześć, " + clientName + "!</h1>");
        out.println("<p>Przyjęliśmy Twoje zapytanie o jazdę próbną modelem: <strong>" + carModel + "</strong>.</p>");
        out.println("<p><a href='index.html'>Powrót na stronę główną</a></p>");
        out.println("</body></html>");
    }
}
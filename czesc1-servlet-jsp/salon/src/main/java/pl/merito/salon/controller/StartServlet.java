package pl.merito.salon.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/start.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("statusMessage", "Wypełnij formularz poniżej, aby umówić jazdę próbną w naszym salonie.");
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String clientName = req.getParameter("clientName");
        String carModel = req.getParameter("carModel");

        req.setAttribute("statusMessage",
                "Cześć, " + clientName + "! Przyjęliśmy zapytanie o jazdę próbną modelem: " + carModel + ".");
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }
}
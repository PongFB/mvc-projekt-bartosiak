package pl.merito.salon.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/lifecycle", loadOnStartup = 1)
public class LifecycleServlet extends HttpServlet {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void init() {
        log("init(): LifecycleServlet utworzony dla aplikacji Salon Samochodowy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int n = counter.incrementAndGet();
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Obsłużono żądań od uruchomienia serwera: " + n);
    }

    @Override
    public void destroy() {
        log("destroy(): LifecycleServlet usuwany, łącznie obsłużono " + counter.get() + " żądań");
    }
}
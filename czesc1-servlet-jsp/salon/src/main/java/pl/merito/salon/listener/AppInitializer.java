package pl.merito.salon.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.merito.salon.model.Brand;
import pl.merito.salon.model.Car;
import pl.merito.salon.repository.BrandRepository;
import pl.merito.salon.repository.CarRepository;

import java.math.BigDecimal;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        BrandRepository brands = new BrandRepository();
        CarRepository cars = new CarRepository();

        Brand Honda = brands.save(new Brand("Honda", "Japonia"));
        Brand Alpine = brands.save(new Brand("Alpine", "Francja"));

        cars.save(new Car(Honda, "CR-V", 2025, new BigDecimal("190000")));
        cars.save(new Car(Honda, "Civic", 2024, new BigDecimal("140000")));
        cars.save(new Car(Alpine, "A110", 2026, new BigDecimal("300000")));

        ServletContext ctx = event.getServletContext();
        ctx.setAttribute("brandRepository", brands);
        ctx.setAttribute("carRepository", cars);
    }
}
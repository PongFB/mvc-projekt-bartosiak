package pl.merito.salon.repository;

import pl.merito.salon.model.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CarRepository {

    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Car> findAll() {
        return cars.values().stream()
                .sorted(Comparator.comparing(Car::getId))
                .toList();
    }

    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    public Car save(Car car) {
        if (car.getId() == null) {
            car.setId(nextId.getAndIncrement());
        }
        cars.put(car.getId(), car);
        return car;
    }

    public void deleteById(Long id) {
        cars.remove(id);
    }
}
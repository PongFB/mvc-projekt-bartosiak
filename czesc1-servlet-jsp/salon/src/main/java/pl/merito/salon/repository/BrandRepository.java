package pl.merito.salon.repository;

import pl.merito.salon.model.Brand;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BrandRepository {

    private final Map<Long, Brand> brands = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Brand> findAll() {
        return brands.values().stream()
                .sorted(Comparator.comparing(Brand::getName))
                .toList();
    }

    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(brands.get(id));
    }

    public Brand save(Brand brand) {
        if (brand.getId() == null) {
            brand.setId(nextId.getAndIncrement());
        }
        brands.put(brand.getId(), brand);
        return brand;
    }
}
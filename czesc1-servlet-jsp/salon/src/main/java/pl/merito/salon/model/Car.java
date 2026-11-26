package pl.merito.salon.model;

import java.math.BigDecimal;

public class Car {
    private Long id;
    private Brand brand;
    private String model;
    private int year;
    private BigDecimal price;
    private boolean available = true;

    public Car() { }

    public Car(Brand brand, String model, int year, BigDecimal price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
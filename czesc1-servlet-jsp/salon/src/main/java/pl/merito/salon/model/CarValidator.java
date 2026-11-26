package pl.merito.salon.model;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public final class CarValidator {

    private CarValidator() { }

    public static Map<String, String> validate(String brandId, String model, String year, String price) {
        Map<String, String> errors = new HashMap<>();

        if (brandId == null || brandId.isBlank()) {
            errors.put("brandId", "Wybierz markę samochodu.");
        } else {
            try {
                Long.parseLong(brandId.trim());
            } catch (NumberFormatException e) {
                errors.put("brandId", "Nieprawidłowy identyfikator marki.");
            }
        }

        if (model == null || model.isBlank()) {
            errors.put("model", "Model samochodu jest wymagany.");
        }

        try {
            int y = Integer.parseInt(year == null ? "" : year.trim());
            int currentYear = Year.now().getValue();
            if (y < 1885 || y > currentYear) {
                errors.put("year", "Rok produkcji musi mieścić się w zakresie 1885–" + currentYear + ".");
            }
        } catch (NumberFormatException e) {
            errors.put("year", "Rok produkcji musi być liczbą całkowitą.");
        }

        try {
            String normalizedPrice = price == null ? "" : price.trim().replace(",", ".");
            BigDecimal p = new BigDecimal(normalizedPrice);
            if (p.compareTo(BigDecimal.ZERO) <= 0) {
                errors.put("price", "Cena samochodu musi być większa od zera.");
            }
        } catch (NumberFormatException e) {
            errors.put("price", "Cena samochodu musi być poprawną liczbą.");
        }

        return errors;
    }
}
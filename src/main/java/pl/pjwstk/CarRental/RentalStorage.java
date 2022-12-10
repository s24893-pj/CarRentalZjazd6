package pl.pjwstk.CarRental;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private final List<Rental> rentals = new ArrayList<>();

    public RentalStorage() {
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}

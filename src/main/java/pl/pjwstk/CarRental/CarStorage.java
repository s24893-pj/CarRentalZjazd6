package pl.pjwstk.CarRental;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarStorage {
    private final List<Car> cars = new ArrayList<>();

    CarStorage() {
        this.cars.add(new Car("Mercedes", "A45", "premium", "12344"));
        this.cars.add(new Car("Mercedes", "CLA", "premium", "12355"));
        this.cars.add(new Car("Audi", "A38L", "standard", "12366"));
        this.cars.add(new Car("VW", "Passat", "standard", "12377"));

        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public Car findByVin(String vin) {
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                return car;
            }
        }
        return null;

    }
}


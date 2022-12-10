package pl.pjwstk.CarRental;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class CarService {

    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;
    private static final int NORMAL_PRICE = 200;
    private static final double STANDARD_RATE = 1.0;
    private static final double PREMIUM_RATE = 1.5;
    private double price;

    public CarService(CarStorage carStorage, RentalStorage rentalStorage) {
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }

    public List<Car> getAllCars() {
        return carStorage.getCars();
    }

    // klasa samochod, ilosc dni (standardowa 200, standart 1.0, premium 1.5)
    public RentalInfo rentACar(User user, String vin, LocalDate startDay, LocalDate endDay) {
        boolean taken = false;
        long numberOfDays = DAYS.between(startDay, endDay);
        if (numberOfDays > 0) {
            if (carStorage.findByVin(vin) != null) {
                System.out.println("samochód istnieje");
            } else {
                System.out.println("samochód nie istnieje :(");
                price = 0;
                System.out.println();
                return null;
            }

            if (!rentalStorage.getRentals().isEmpty()) {
                for (int i = 0; i < rentalStorage.getRentals().size(); i++) {
                    if (carStorage.findByVin(vin) == rentalStorage.getRentals().get(i).getCar()) {
                        System.out.println("zajęte");
                        System.out.println();
                        taken = true;
                        price = 0;
                        return null;
                    }
                }
            }
            if (!taken) {
                System.out.println("nie zajęte, udało się wynająć!!!");
                rentalStorage.getRentals().add(new Rental(user, carStorage.findByVin(vin)));
                if (carStorage.findByVin(vin).getKlasa().equals("standard")) {
                    System.out.println("wynajęty samochód: \n" + carStorage.findByVin(vin).toString() + "\n" + "Cena " +
                            "samochodu: " + (NORMAL_PRICE * numberOfDays * STANDARD_RATE));
                    price = NORMAL_PRICE * numberOfDays * STANDARD_RATE;
                } else if (carStorage.findByVin(vin).getKlasa().equals("premium")) {
                    System.out.println("wynajęty samochód: \n" + carStorage.findByVin(vin).toString() + "\n" + "Cena " +
                            "samochodu: " + (NORMAL_PRICE * numberOfDays * PREMIUM_RATE));
                    price = NORMAL_PRICE * numberOfDays * PREMIUM_RATE;
                }
                System.out.println();
            }
            return new RentalInfo(price, startDay, endDay);
        }else{
            System.out.println("Data końcowa nie może być mniejsza od daty początkowej");
            System.out.println();
            return null;
        }
    }

}

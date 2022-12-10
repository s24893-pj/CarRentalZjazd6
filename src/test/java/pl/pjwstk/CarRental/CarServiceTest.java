package pl.pjwstk.CarRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    // co sie dzieje z wynajmem jak ktos wynajmuje
    // co sie dzieje jak samochód nie istnieje
    // end date wczesniej niz start date
    // end date == start date
    // wszystko działa poprawnie i się wynajął
    // jaka jest cena samochodu premium
    // jaka jest cena samochodu standard

    private CarStorage carstorage;
    private RentalStorage rentalstorage;

    private final CarStorage carStorage = new CarStorage();

    private final RentalStorage rentalStorage = new RentalStorage();
    private final CarService carService = new CarService(carStorage,  rentalStorage);

    @DisplayName("Cena samochód standard")
    @Test
    void carServiceTest(){
        //Given
        User user = new User("1");
        String vin = "12366";
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo.getPrice()).isEqualTo(800);
    }
    @DisplayName("Samochód nie istnieje")
    @Test
    void carServiceTest2(){
        //Given
        User user = new User("1");
        String vin = "123";
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo).isEqualTo(null);
    }
    @DisplayName("Samochód Wynajęty")
    @Test
    void carServiceTest3(){
        //Given
        User user = new User("1");
        String vin = "12366";
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        carService.rentACar(user, vin, startDate, endDate);
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo).isEqualTo(null);
    }
    @DisplayName("Data końcowa mniejsza od daty początkowej")
    @Test
    void carServiceTest4(){
        //Given
        User user = new User("1");
        String vin = "12366";
        LocalDate startDate = LocalDate.of(2022, 12, 6);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo).isEqualTo(null);
    }
    @DisplayName("Cena Samochód Premium")
    @Test
    void carServiceTest5(){
        //Given
        User user = new User("1");
        String vin = "12355";
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo.getPrice()).isEqualTo(1200);
    }
    @DisplayName("vin to null")
    @Test
    void carServiceTest6(){
        //Given
        User user = new User("1");
        String vin = null;
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo).isEqualTo(null);
    }

}
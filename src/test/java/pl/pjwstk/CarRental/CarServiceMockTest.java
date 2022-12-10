package pl.pjwstk.CarRental;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceMockTest {

    //    @Captor
//    private ArgumentCaptor<Integer> CarCaptor;
    @Mock
    private CarStorage carStorage;
    @Mock
    private RentalStorage rentalStorage;
    @InjectMocks
    private CarService carService;

    @DisplayName("Cena samochód standard")
    @Test
    void carServiceTest() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new User("2"), new Car()));
        Car car = new Car("Audi", "a3", "standard", "1231");
        when(carStorage.findByVin(any())).thenReturn(car);
        when(rentalStorage.getRentals()).thenReturn(rentals);

        User user = new User("1");
        String vin = null;
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);

        //Then
        assertThat(rentalInfo.getPrice()).isEqualTo(800);
    }

    @DisplayName("Samochód nie istnieje")
    @Test
    void carServiceTest2() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new User("2"), new Car()));
        Car car = new Car("Audi", "a3", "standard", "1231");
        when(carStorage.findByVin(any())).thenReturn(null);
//        when(rentalStorage.getRentals()).thenReturn(rentals);

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
    void carServiceTest3() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        Car car = new Car("Audi", "a3", "standard", "1231");
        rentals.add(new Rental(new User("2"), car));
        when(carStorage.findByVin(any())).thenReturn(car);
        when(rentalStorage.getRentals()).thenReturn(rentals);


        User user = new User("1");
        String vin = "12366";
        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 5);
        //When
//        carService.rentACar(user, vin, startDate, endDate);
        RentalInfo rentalInfo = carService.rentACar(user, vin, startDate, endDate);
        //Then
        assertThat(rentalInfo).isEqualTo(null);
    }

    @DisplayName("Data końcowa mniejsza od daty początkowej")
    @Test
    void carServiceTest4() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new User("2"), new Car()));
        Car car = new Car("Audi", "a3", "standard", "1231");
//        when(carStorage.findByVin(any())).thenReturn(car);
//        when(rentalStorage.getRentals()).thenReturn(rentals);

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
    void carServiceTest5() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new User("2"), new Car()));
        Car car = new Car("Audi", "a3", "premium", "1231");
        when(carStorage.findByVin(any())).thenReturn(car);
        when(rentalStorage.getRentals()).thenReturn(rentals);

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
    void carServiceTest6() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new User("2"), new Car()));
        Car car = new Car("Audi", "a3", "standard", "1231");
        when(carStorage.findByVin(any())).thenReturn(null);

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

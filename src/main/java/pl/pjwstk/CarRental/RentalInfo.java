package pl.pjwstk.CarRental;

import java.time.LocalDate;

public class RentalInfo {

    private double price;
    private LocalDate startDay;
    private LocalDate endDay;


    RentalInfo (double price, LocalDate startDay, LocalDate endDay){
    this.price = price;
    this.startDay = startDay;
    this.endDay = endDay;
    }

    public double getPrice(){
        return price;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    @Override
    public String toString() {
        return "RentalInfo{" +
                "price=" + price +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}

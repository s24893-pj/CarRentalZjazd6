package pl.pjwstk.CarRental;

public class Car {
    public String getVin;
    private String marka;
    private String model;
    private String klasa;
    private String vin;

    public Car(String marka, String model, String klasa, String vin) {
        this.marka = marka;
        this.model = model;
        this.klasa = klasa;
        this.vin = vin;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", klasa='" + klasa + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}

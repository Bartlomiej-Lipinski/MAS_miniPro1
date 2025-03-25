import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("Audi","A4","2.0 TDI",60,4, LocalDate.of(2010,1,1),100000,"KR12345",200);
        System.out.println(car);
        Car.serializeCars();
        Car car1 = new Car("BMW","X5","3.0 TDI",80,4, LocalDate.of(2015,1,1),50000,"KR54321",250);
        Car.deserializeCars();
        System.out.println(Car.getCarByBrand("Audi"));
        System.out.println(Car.getCarByBrand("BMW"));
        Car.removeCar(car);
        System.out.println(Car.getCarByBrand("Audi"));
    }

}

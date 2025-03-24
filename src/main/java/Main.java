import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("Audi","A4","2.0 TDI",60,4, LocalDate.of(2010,1,1),100000,"KR12345",200);
        System.out.println(car.getMark());
    }

}

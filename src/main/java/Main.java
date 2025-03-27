import java.io.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create a new car instance using the first constructor
        Car car = new Car("Audi", "A4", "2.0 TDI", 60, 4, LocalDate.of(2010, 1, 1), 100000, "KR12345");

        // Test getters
        System.out.println("Brand: " + car.getBrand());
        System.out.println("Model: " + car.getModel());
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Fuel Tank Capacity: " + car.getFuelTankCapacity());
        System.out.println("Number of Doors: " + car.getNumberOfDoors());
        System.out.println("Production Date: " + car.getProductionDate());
        System.out.println("Mileage: " + car.getMileage());
        System.out.println("Registration Number: " + car.getRegistrationNumber());
        System.out.println("Car Age: " + car.getCarAge());
        System.out.println("Max Emission: " + Car.getMaxEmission());

        // Test setters
        car.setBrand("BMW");
        car.setModel("X5");
        car.setEngine("3.0 TDI");
        car.setFuelTankCapacity(80);
        car.setNumberOfDoors(5);
        car.setProductionDate(LocalDate.of(2015, 1, 1));
        car.setRegistrationNumber("KR54321");
        System.out.println("Updated Car: " + car);

        // Test addMileage
        car.addMileage(150000);
        System.out.println("Updated Mileage: " + car.getMileage());

        // Test static methods
        Car.setMaxEmissionCO2(250);
        System.out.println("Updated Max Emission: " + Car.getMaxEmission());

        // Test car list operations
        Car car2 = new Car("Mercedes", "C-Class", "2.0", 70, 4, LocalDate.of(2018, 1, 1), 50000);
        System.out.println("Cars by Brand (BMW): " + Car.getCarByBrand("BMW"));
        Car.removeCar(car);
        try {
            System.out.println("Cars by Brand (BMW) after removal: " + Car.getCarByBrand("BMW"));
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }

        // Test serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cars.bin"))) {
            Car.writeExtent(oos);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cars.bin"))) {
            Car.readExtent(ois);
        }
        System.out.println("Cars after deserialization: " + Car.getCarByBrand("Mercedes"));
    }
}
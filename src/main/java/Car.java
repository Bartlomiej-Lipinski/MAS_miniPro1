import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Car implements Serializable {
    private static List<Car> cars = new ArrayList<>();
    private String brand;
    private String model;
    private String engine;
    private int fuelTankCapacity;
    private int numberOfDoors;
    private LocalDate productionDate;
    private List<Integer> mileageList;
    private String registrationNumber;
    private static int maxEmissionOfCO2 = 200;

    public Car(String brand, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage, String registrationNumber) {
        setBrand(brand);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        if (mileage < 0 ) {
            throw new IllegalArgumentException("Mileage is negative");
        }
        mileageList.add(mileage);
        setRegistrationNumber(registrationNumber);
        addCar(this);
    }
    public Car(String brand, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage) {
        setBrand(brand);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        if (mileage < 0 ) {
            throw new IllegalArgumentException("Mileage is negative");
        }
        mileageList.add(mileage);
        addCar(this);
    }
    public static void setMaxEmissionCO2(int maxEmission) {
        if (maxEmission > 500) {
            throw new IllegalArgumentException("Emission is too high");
        } else {
            Car.maxEmissionOfCO2 = maxEmission;
        }
    }
    public void setBrand(String brand) {
        checkForNullValue(brand, "Brand is null");
        checkStringForEmptyAndBlank(brand, "Brand Contains only spaces or is empty");
        this.brand = brand;
    }
    public void setModel(String model) {
        checkForNullValue(model, "Model is null");
        checkStringForEmptyAndBlank(model, "Model is empty or contains only spaces");
        this.model = model;
    }
    public void setEngine(String engine) {
        checkForNullValue(engine, "Engine is null");
        checkStringForEmptyAndBlank(engine, "Engine is empty or contains only spaces");
        this.engine = engine;
    }
    public void setFuelTankCapacity(int fuelTankCapacity) {
        if (fuelTankCapacity < 0 || fuelTankCapacity > 100_000) {
            throw new IllegalArgumentException("Fuel tank capacity is negative");
        }
        this.fuelTankCapacity = fuelTankCapacity;
    }
    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2) {
            throw new IllegalArgumentException("Number of doors is too low");
        }
        this.numberOfDoors = numberOfDoors;
    }
    public void setProductionDate(LocalDate productionDate) {
        if (productionDate == null) {
            throw new IllegalArgumentException("Production date is null");
        }
        if (productionDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Production date is in the future");
        }
        this.productionDate = productionDate;
    }
    public void setRegistrationNumber(String registrationNumber) {
        checkForNullValue(registrationNumber, "Registration number is null");
        checkStringForEmptyAndBlank(registrationNumber, "Registration number is empty or contains only spaces");
        Pattern pattern = Pattern.compile("^[A-Z]{2}\\d{5}$");
        if (!pattern.matcher(registrationNumber).matches()) {
            throw new IllegalArgumentException("Registration number is invalid");
        }
        this.registrationNumber = registrationNumber;
    }
    public void addMileage(int mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage is negative");
        }
        if (mileageList.getLast() > mileage) {
            throw new IllegalArgumentException("Mileage cannot be lower than the previous one");
        }
        mileageList.add(mileage);
    }
    public static List<Car> getCarByBrand(String brand) {
        List<Car> carsByMark = cars.stream().filter(carToFindByMark -> carToFindByMark.brand.equals(brand)).toList();
        if (carsByMark.isEmpty()) {
            throw new IllegalArgumentException("Car with mark " + brand + " not found");
        }
        return carsByMark;
    }
    public static void removeCar(Car car) {
        cars.remove(car);
    }
    private static void addCar(Car car) {
        cars.add(car);
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getEngine() {
        return engine;
    }
    public int getFuelTankCapacity() {
        return fuelTankCapacity;
    }
    public int getNumberOfDoors() {
        return numberOfDoors;
    }
    public LocalDate getProductionDate() {
        return productionDate;
    }
    public int getMileage() {
        return mileageList.getLast();
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public static int getMaxEmission() {
        return maxEmissionOfCO2;
    }
    public int getCarAge() {
        int age = LocalDate.now().getYear() - productionDate.getYear();
        if (age < 0) {
            throw new IllegalArgumentException("Car age is negative");
        }
        return age;
    }
    public static void writeExtent(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(cars);
    }
    public static void readExtent(ObjectInputStream stream) throws IOException,ClassNotFoundException {
        cars = (List<Car>) stream.readObject();
    }
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", fuelTankCapacity=" + fuelTankCapacity +
                ", numberOfDoors=" + numberOfDoors +
                ", productionDate=" + productionDate +
                ", mileageList=" + mileageList +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
    private void checkStringForEmptyAndBlank(String string, String message) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        if (string.isBlank()) {
            throw new IllegalArgumentException(message);
        }

    }
    private void checkForNullValue(String string, String message) {
        if (string == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
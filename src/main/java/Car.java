import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {
    private static final List<Car> cars = new ArrayList<>();
    private String brand;
    private String model;
    private String engine;
    private int fuelTankCapacity;
    private int numberOfDoors;
    private LocalDate productionDate;
    private List<Integer> mileageList;
    private String registrationNumber;
    private static int maxEmmissionOfCO2 = 200;

    public Car(String brand, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage, String registrationNumber, int maxSpeed) {
        setBrand(brand);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        addMileage(mileage);
        setRegistrationNumber(registrationNumber);
        addCar(this);
        setMaxEmissionCO2(maxSpeed);
    }

    public Car(String brand, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage) {
        setBrand(brand);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        addMileage(mileage);
        addCar(this);
    }

    public static void setMaxEmissionCO2(int maxEmission) {
        if (maxEmission > 500) {
            throw new IllegalArgumentException("Emission is too high");
        } else {
            Car.maxEmmissionOfCO2 = maxEmission;
        }
    }

    public void setBrand(String brand) {
        String markCheck = brand.replace(" ", "");
        if (markCheck.isEmpty()) {
            throw new IllegalArgumentException("Brand is empty");
        }
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setFuelTankCapacity(int fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void addMileage(int mileage) {
        if (mileageList.isEmpty()) {
            mileageList.add(mileage);
        }
        if (mileageList.getLast() > mileage) {
            throw new IllegalArgumentException("Mileage cannot be lower than the previous one");
        }
    }

    public void setMileage(List<Integer> mileage) {
        this.mileageList = mileage;
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
        return maxEmmissionOfCO2;
    }

    public int getCarAge() {
        int age = LocalDate.now().getYear() - productionDate.getYear();
        if (age < 0) {
            throw new IllegalArgumentException("Car age is negative");
        }
        return age;
    }

    public static void serializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
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
}
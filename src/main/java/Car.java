import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {
    private static final List<Car> cars = new ArrayList<>();
    private String mark;
    private String model;
    private String engine;
    private int fuelTankCapacity;
    private int numberOfDoors;
    private LocalDate productionDate;
    private List<Integer> mileageList;
    private String registrationNumber;
    private static int maxSpeed;

    public Car(String mark, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage, String registrationNumber, int maxSpeed) {
        setMark(mark);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        addMileage(mileage);
        setRegistrationNumber(registrationNumber);
        addCar(this);
        setMaxSpeed(maxSpeed);
    }

    public Car(String mark, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage) {
        setMark(mark);
        setModel(model);
        setEngine(engine);
        setFuelTankCapacity(fuelTankCapacity);
        setNumberOfDoors(numberOfDoors);
        setProductionDate(productionDate);
        this.mileageList = new ArrayList<>();
        addMileage(mileage);
        addCar(this);
    }

    public static void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 500) {
            throw new IllegalArgumentException("Max speed is too high");
        } else {
            Car.maxSpeed = maxSpeed;
        }
    }

    public void setMark(String mark) {
        String markCheck = mark.replace(" ", "");
        if (markCheck.isEmpty()) {
            throw new IllegalArgumentException("Mark is empty");
        }
        this.mark = mark;
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

    public static List<Car> getCarByMark(String mark) {
        List<Car> carsByMark = cars.stream().filter(carToFindByMark -> carToFindByMark.mark.equals(mark)).toList();
        if (carsByMark.isEmpty()) {
            throw new IllegalArgumentException("Car with mark " + mark + " not found");
        }
        return carsByMark;
    }

    public static void removeCar(Car car) {
        cars.remove(car);
    }

    private static void addCar(Car car) {
        cars.add(car);
    }

    public String getMark() {
        return mark;
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

    public static int getMaxSpeed() {
        return maxSpeed;
    }

    public int getCarAge() {
        int age = LocalDate.now().getYear() - productionDate.getYear();
        if (age < 0) {
            throw new IllegalArgumentException("Car age is negative");
        }
        return age;
    }

    private static void serializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void deserializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
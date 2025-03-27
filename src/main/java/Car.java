import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    /**
     * @param brand
     * @param model
     * @param engine
     * @param fuelTankCapacity
     * @param numberOfDoors
     * @param productionDate
     * @param mileage
     * @param registrationNumber
     * @param maxSpeed
     * This constructor creates a car object with the given parameters
     */
    public Car(String brand, String model, String engine, int fuelTankCapacity, int numberOfDoors, LocalDate productionDate, int mileage, String registrationNumber, int maxEmission) {
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
        setMaxEmissionCO2(maxEmission);
    }

    /**
     * @param brand
     * @param model
     * @param engine
     * @param fuelTankCapacity
     * @param numberOfDoors
     * @param productionDate
     * @param mileage
     * This constructor creates a car object with the given parameters without registration number and max emission
     */
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

/**
 * @param maxEmission
 * This method sets the maximum emission of CO2
 */
    public static void setMaxEmissionCO2(int maxEmission) {
        if (maxEmission > 500) {
            throw new IllegalArgumentException("Emission is too high");
        } else {
            Car.maxEmmissionOfCO2 = maxEmission;
        }
    }

    /**
     * Sets the brand of the car.
     *
     * @param brand the brand to set
     * @throws IllegalArgumentException if the brand is empty or contains only spaces
     */
    public void setBrand(String brand) {
        checkForEmptyString(brand, "Brand is empty");
        checkForOnlySpaces(brand, "Brand contains only spaces");
        this.brand = brand;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**
     * Sets the engine of the car.
     *
     * @param engine the engine to set
     * @throws IllegalArgumentException if the engine is empty or contains only spaces
     */
    public void setEngine(String engine) {
        checkForEmptyString(engine, "Engine is empty");
        checkForOnlySpaces(engine, "Engine contains only spaces");
        this.engine = engine;
    }
    /**
     * This method sets the fuel tank capacity of the car
     * @throws IllegalArgumentException if the fuel tank capacity is negative
     * @param fuelTankCapacity
     */
    public void setFuelTankCapacity(int fuelTankCapacity) {
        if (fuelTankCapacity < 0) {
            throw new IllegalArgumentException("Fuel tank capacity is negative");
        }
        this.fuelTankCapacity = fuelTankCapacity;
    }
    /**
     * This method sets the number of doors of the car
     * @throws IllegalArgumentException if the number of doors is less than 2
     * @param numberOfDoors
     */
    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2) {
            throw new IllegalArgumentException("Number of doors is too low");
        }
        this.numberOfDoors = numberOfDoors;
    }
    /**
     * This method sets the production date of the car
     * @throws IllegalArgumentException if the production date is in the future
     * @param productionDate
     */
    public void setProductionDate(LocalDate productionDate) {
        if (productionDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Production date is in the future");
        }
        this.productionDate = productionDate;
    }
    /**
     * This method sets the registration number of the car by Polish standards
     * @throws IllegalArgumentException if the registration number is invalid
     * @param registrationNumber
     */
    public void setRegistrationNumber(String registrationNumber) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}\\d{5}$");
        if (!pattern.matcher(registrationNumber).matches()) {
            throw new IllegalArgumentException("Registration number is invalid");
        }
        this.registrationNumber = registrationNumber;
    }
    /**
     * adds the milage read from the car and adds it to the list of milages of the car
     * @throws IllegalArgumentException if the mileage is lower than the previous one
     * @param mileage
     */
    public void addMileage(int mileage) {
        if (mileageList.isEmpty()) {
            mileageList.add(mileage);
        }
        if (mileageList.getLast() > mileage) {
            throw new IllegalArgumentException("Mileage cannot be lower than the previous one");
        }
    }

    /**
     * Retrieves a list of cars by the specified brand.
     *
     * @param brand the brand of the cars to retrieve
     * @return a list of cars that match the specified brand
     * @throws IllegalArgumentException if no cars with the specified brand are found
     */
    public static List<Car> getCarByBrand(String brand) {
        List<Car> carsByMark = cars.stream().filter(carToFindByMark -> carToFindByMark.brand.equals(brand)).toList();
        if (carsByMark.isEmpty()) {
            throw new IllegalArgumentException("Car with mark " + brand + " not found");
        }
        return carsByMark;
    }
    /**
     * @param car the car to remove as a object
     * This method removes the car from the list of cars
     * @throws IllegalArgumentException if the car is not found
     */
    public static void removeCar(Car car) {
        cars.remove(car);
    }

    /**
     * Retrieves a list of cars by the specified model.
     * @param car the car to remove as a object
     */
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
    /**
     * 
     * @return the maximum emission of CO2
     */
    public static int getMaxEmission() {
        return maxEmmissionOfCO2;
    }
    /**
     * 
     * @return the age of the car
     * This method calculates the age of the car by subtracting the production date from the current year
     * If the age is negative, an exception is thrown
     * @throws IllegalArgumentException if the car age is negative
     */
    public int getCarAge() {
        int age = LocalDate.now().getYear() - productionDate.getYear();
        if (age < 0) {
            throw new IllegalArgumentException("Car age is negative");
        }
        return age;
    }
    /**
     * Serializes the cars to a file. Used for Class extension.
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if the file is not found
     */

    public static void serializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * Deserializes the cars from the file. Used for Class extension.
     * @throws IOException if an I/O error occurs
     */
    public static void deserializeCars() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("cars.ser"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cars);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * @return a String representation of the car
     * This method returns a string representation of the car
     * It includes the brand, model, engine, fuel tank capacity, number of doors, production date, mileage list and registration number if it exists
     */
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
    /**
     * 
     * @param string 
     * @param message
     * This method checks if the string is empty and throws an exception if it is
     */
    private void checkForEmptyString(String string, String message) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    /**
     * 
     * @param string
     * @param message
     * This method checks if the string contains only spaces and throws an exception if it does
     */
    }
    private void checkForOnlySpaces(String string, String message) {
        if (string.replace(" ", "").isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
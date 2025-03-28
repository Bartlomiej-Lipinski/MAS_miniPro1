import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCar
{
    Car car = new Car("Audi","A4","2.0 TDI",60,4, LocalDate.of(2010,1,1),100000,"KR12345");

    @Test
    void testConstructor(){
        assertEquals(Car.class, car.getClass());
    }
    @Test
    void testGetBrand(){
        assertEquals("Audi",car.getBrand());
    }
    @Test
    void testGetModel(){
        assertEquals("A4",car.getModel());
    }
    @Test
    void testGetEngine(){
        assertEquals("2.0 TDI",car.getEngine());
    }
    @Test
    void testGetFuelTankCapacity(){
        assertEquals(60,car.getFuelTankCapacity());
    }
    @Test
    void testGetNumberOfDoors(){
        assertEquals(4,car.getNumberOfDoors());
    }
    @Test
    void testGetProductionDate(){
        assertEquals(LocalDate.of(2010,1,1),car.getProductionDate());
    }
    @Test
    void testGetRegistrationNumber(){
        assertEquals("KR12345",car.getRegistrationNumber());
    }
    @Test
    void testGetMileage(){
        assertEquals(100000,car.getMileage());
    }
    @Test
    void testSetRegistrationNumber(){
        car.setRegistrationNumber("KR54321");
        assertEquals("KR54321",car.getRegistrationNumber());
    }
    @Test
    void testAddMileage(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> car.addMileage(1));
    }
    @Test
    void testGetCarAge(){
        assertEquals(15,car.getCarAge());
    }
    @Test
    void testGetMaxEmission(){
        assertEquals(200,Car.getMaxEmission());
    }
    @Test
    void testGetCarByBrand(){
        boolean flag = false;
        for(Car c: Car.getCarByBrand("Audi")){
            if(c.getBrand().equals("Audi")){
                flag = true;
                break;
            }else {
                flag = false;
            }
        }
        assertTrue(flag);
    }
    @Test
    void TestSetMark(){
        Assertions.assertThrows(IllegalArgumentException.class,(()->car.setBrand("     ")));
    }
}

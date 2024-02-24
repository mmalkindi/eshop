package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    private CarService carService;
    private static final Car[] carList = new Car[10];

    @BeforeEach
    void setUp() {
        carService = new CarServiceImpl(new CarRepository());
        String[] carNames = {"992 Dakar", "992 GT3 RS", "Countach", "Roma", "Jesko", "One:1", "Nevera" , "GT40 MK.II" ,"GT", "Cullinan"};
        String[] carColors = {"Quad-color", "Red", "Silver", "Velvet", "Orange", "Red-White", "Cyan" , "Gulf" ,"Dark Blue", "White"};
        int[] carQuantities = {100, 25, 30, 55, 22, 5, 1, 100, 23, 10000};

        for (int i=0; i<5; i++) {
            Car car = new Car();
            car.setCarName(carNames[i]);
            car.setCarColor(carColors[i]);
            car.setCarQuantity(carQuantities[i]);
            carList[i] = car;
            carService.create(car);
        }
    }

    @Test
    void testUpdate() {
        Car updatedCar = carList[0];
        updatedCar.setCarName("CHANGED-NAME");
        updatedCar.setCarColor("CHANGED-COLOR");
        updatedCar.setCarQuantity(10);

        carService.update(carList[0].getCarId(), updatedCar);
        assertEquals(updatedCar.getCarId(), carList[0].getCarId());
        assertEquals(updatedCar.getCarColor(), carList[0].getCarColor());
        assertEquals(updatedCar.getCarQuantity(), carList[0].getCarQuantity());
    }

    @Test
    void testDeleteAndFind() {
        Car carToDelete = carList[0];
        carService.deleteCarById(carToDelete.getCarId());

        Car foundCar = carService.findById(carToDelete.getCarId());
        assertNull(foundCar);
    }

    @Test
    void testFindById() {
        Car foundCar = carService.findById(carList[0].getCarId());
        assertEquals(carList[0], foundCar);
    }

    @Test
    void testFindByIdNotFound() {
        Car foundCar = carService.findById("THIS-SHOULD-NOT-EXIST");
        assertNull(foundCar);
    }

    @Test
    void testFindAll() {
        List<Car> carFindList = carService.findAll();
        int index=0;
        while (index < carFindList.size()) {
            assertEquals(carFindList.get(index), carList[index]);
            index++;
        }
    }
}

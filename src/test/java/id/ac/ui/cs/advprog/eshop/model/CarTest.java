package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CarTest {
    Car car;
    @BeforeEach
    void setUp() {
        this.car = new Car();
        car.setCarId("5b365e8a-565b-4f8b-9435-71126d071ffc");
        car.setCarName("Porsche 911 GT3 RS");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testGetCarIdEquals() {
        assertEquals("5b365e8a-565b-4f8b-9435-71126d071ffc", car.getCarId());
    }
    @Test
    void testGetCarIdNotEquals() {
        assertNotEquals("6ba5eee5-99a4-43e2-9be2-26f02557d742", car.getCarId());
    }

    @Test
    void testGetCarNameEquals() {
        assertEquals("Porsche 911 GT3 RS", car.getCarName());
    }
    @Test
    void testGetCarNameNotEquals() {
        assertNotEquals("Tesla Model S Plaid", car.getCarName());
    }

    @Test
    void testGetCarColorEquals() {
        assertEquals("Red", car.getCarColor());
    }
    @Test
    void testGetCarColorNotEquals() {
        assertNotEquals("Blue", car.getCarColor());
    }

    @Test
    void testGetCarQuantityEquals() {
        assertEquals(5, car.getCarQuantity());
    }
    @Test
    void testGetCarQuantityNotEquals() {
        assertNotEquals(15, car.getCarQuantity());
    }
}

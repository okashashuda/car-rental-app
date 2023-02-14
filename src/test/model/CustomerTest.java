package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer testCustomer1;
    private Customer testCustomer2;

    @BeforeEach
    public void setUp() {
        testCustomer1 = new Customer("Ken", "Adams", 25); //valid driver
        testCustomer2 = new Customer("Joe", "Black", 17); //invalid driver
    }

    @Test
    public void testConstructor() {
        assertEquals("Ken", testCustomer1.getFirstName());
        assertEquals("Adams", testCustomer1.getLastName());
        assertEquals(25, testCustomer1.getAge());

        assertEquals("Joe", testCustomer2.getFirstName());
        assertEquals("Black", testCustomer2.getLastName());
        assertEquals(17, testCustomer2.getAge());
    }

    @Test
    public void testIsValidDriver() {
        assertTrue(testCustomer1.isValidRenter());
        assertFalse(testCustomer2.isValidRenter());
    }
}

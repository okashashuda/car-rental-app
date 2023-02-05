package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Ken", "Adams", 25);
    }

    @Test
    public void testConstructor() {
        assertEquals("Ken", testCustomer.getFirstName());
        assertEquals("Adams", testCustomer.getLastName());
        assertEquals(25, testCustomer.getAge());
        assertTrue(testCustomer.isValidRenter());
    }
}

package moviestore;

import static org.junit.Assert.*;

import org.junit.Test;
public class CustomersTest {
    @Test
    public void shouldCreateProperCustomer()
    {
        Customer c = new Customer("Bianca Rossetti", 1000);
        assertEquals("Bianca Rossetti", c.getName());
    }
}

package moviestore;
import moviestore.products.*;
import moviestore.*;
import moviestore.exceptions.LoaderFailedException;
import moviestore.discounts.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import org.junit.Test;

public class TestDiscountStrategy {
    /**
     * BIANCA
     */

    @Test
    public void testTenDiscount()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Customer c = new Customer("Bianca", 20000);
        IDiscountStrategy strat = new TenDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
        assertEquals(5000, c.getPoints());
        assertEquals(30.50, finalPrice, 0.0001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTenDiscountException()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Customer c = new Customer("Bianca", 10000);
        IDiscountStrategy strat = new TenDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
    }

    @Test
    public void testTwentyDiscount()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Customer c = new Customer("Bianca", 30000);
        IDiscountStrategy strat = new TwentyDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
        assertEquals(5000, c.getPoints());
        assertEquals(20.50, finalPrice, 0.0001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTwentyDiscountException()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Customer c = new Customer("Bianca", 20000);
        IDiscountStrategy strat = new TwentyDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
    }

     /**
      * AMY
      */
}

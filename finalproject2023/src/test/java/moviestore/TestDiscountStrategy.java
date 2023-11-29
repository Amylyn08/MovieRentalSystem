package moviestore;

import moviestore.products.*;
import moviestore.*;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.FileLoader;
import moviestore.discounts.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class TestDiscountStrategy {

    @Test
    public void testFiftyDolllarDiscountPoints() throws LoaderFailedException {
        Customer cus = new Customer("Tester customer", 120000);
        IDiscountStrategy discount = new FiftyDollarDiscount();
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        double price = discount.finalPrice(cus, movies.get(33));
        assertEquals(20000, cus.getPoints(), 0.0001);
        assertEquals(59.99, price, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fiftyDollarDiscountException() throws LoaderFailedException {
        Customer cus = new Customer("Tester Customer", 2);
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IDiscountStrategy discount = new FiftyDollarDiscount();
        double price = discount.finalPrice(cus, movies.get(33));
    }

    @Test
    public void testFiveDollarDiscount() throws LoaderFailedException {
        Customer cus = new Customer("Tester customer", 15000);
        IDiscountStrategy discount = new FiveDollarDiscount();
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        double price = discount.finalPrice(cus, movies.get(0));
        assertEquals(5000, cus.getPoints(), 0.0001);
        assertEquals(35.50, price, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiveDollarDiscountException() throws LoaderFailedException {
        Customer cus = new Customer("Broke billy", 1000);
        IDiscountStrategy discount = new FiveDollarDiscount();
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        double price = discount.finalPrice(cus, movies.get(0));
    }

    @Test
    public void testTenDiscount() {
        Movie m = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 5,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        Customer c = new Customer("Bianca", 20000);
        IDiscountStrategy strat = new TenDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
        assertEquals(5000, c.getPoints());
        assertEquals(30.50, finalPrice, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTenDiscountException() {
        Movie m = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 5,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        Customer c = new Customer("Bianca", 10000);
        IDiscountStrategy strat = new TenDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
    }

    @Test
    public void testTwentyDiscount() {
        Movie m = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 5,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        Customer c = new Customer("Bianca", 30000);
        IDiscountStrategy strat = new TwentyDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
        assertEquals(5000, c.getPoints());
        assertEquals(20.50, finalPrice, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwentyDiscountException() {
        Movie m = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 5,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        Customer c = new Customer("Bianca", 20000);
        IDiscountStrategy strat = new TwentyDollarDiscount();
        double finalPrice = strat.finalPrice(c, m);
    }
}

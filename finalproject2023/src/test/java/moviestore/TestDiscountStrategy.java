package moviestore;

import moviestore.products.*;
import moviestore.*;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.FileLoader;
import moviestore.discounts.*;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import java.io.*;

public class TestDiscountStrategy {
    /**
     * BIANCA
     */

    /**
     * AMY
     */
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
}

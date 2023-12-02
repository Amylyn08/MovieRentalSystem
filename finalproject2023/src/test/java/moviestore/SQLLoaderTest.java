package moviestore;

import moviestore.products.*;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.SQLLoader;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class SQLLoaderTest {
    @Test
    public void testRightDigital() throws LoaderFailedException {
        Movie dvdMovie = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 2,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        Movie digitalMovie = new DigitalMovie("Elf", "Comedy", 97,
                "A human raised by elves at the North Pole embarks on a journey to New York City to find his real father.",
                240, 55, 6.99, 1800, 2,"https://www.youtube.com/watch?v=nfKO9rYDmE8");
        SQLLoader loader = new SQLLoader("A2233420", "KimNamjoon2021");
        List<Movie> loadedMovies = loader.loadMovies();
        assertTrue(dvdMovie.equals(loadedMovies.get(0)));
        assertTrue(digitalMovie.equals(loadedMovies.get(loadedMovies.size() - 1)));
    }

    @Test
    public void testGetCustomers() throws LoaderFailedException {
        Customer first = new Customer("John Doe", 1500);
        Customer last = new Customer("Ella Hall", 1250);
        SQLLoader loader = new SQLLoader("A2233420", "KimNamjoon2021");
        List<Customer> loadedCustomers = loader.loadCustomers();
        assertTrue(first.equals(loadedCustomers.get(0)));
        assertTrue(last.equals(loadedCustomers.get(loadedCustomers.size() - 1)));
    }
}

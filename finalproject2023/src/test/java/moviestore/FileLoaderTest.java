package moviestore;

import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.*;
import moviestore.products.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FileLoaderTest {

    @Test
    public void testRightDigital() throws LoaderFailedException {
        Movie digitalMovie = new DigitalMovie("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 1800, 2, "https://www.youtube.com/watch?v=nfKO9rYDmE8");
        FileLoader loader = new FileLoader();
        List<Movie> loadedMovies = loader.loadMovies();
        // String firstMovie = loadedMovies.get(0).getTitle()
        assertTrue(digitalMovie.equals(loadedMovies.get(0)));
    }

    @Test
    public void testRightDVD() throws LoaderFailedException {
        Movie dvdMovie = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 3, "https://www.youtube.com/watch?v=nfKO9rYDmE8");
    }

    @Test
    public void testCustomer() throws LoaderFailedException {
        Customer cus = new Customer("Christopher Martinez", 1300);
        FileLoader loader = new FileLoader();
        List<Customer> loadedCustomers = loader.loadCustomers();
        assertTrue(cus.equals(loadedCustomers.get(10)));
    }
}

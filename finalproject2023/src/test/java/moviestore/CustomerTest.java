package moviestore;

import org.junit.Test;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;

import static org.junit.Assert.*;
import java.util.*;

public class CustomerTest {
    @Test
    public void customerCreationTest()
    {
        Customer cus = new Customer("Bianca", 1000);
        assertEquals(1000, cus.getPoints());
        assertEquals("Bianca", cus.getName());
    }

    @Test
    public void customerRentAMovie()
    {
        Customer cus = new Customer("Bianca", 1000);
        Movie m1 = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        Movie m2 = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        
        cus.rentMovie(m1);
        assertEquals(m1, cus.getRentedMovies().get(0));
        cus.rentMovie(m2);
        assertEquals(m2, cus.getRentedMovies().get(1));
        assertEquals(9100, cus.getPoints());
    }

    @Test (expected = IllegalArgumentException.class)
    public void customerReturnAMovieException()
    {
        Customer cus = new Customer("Bianca", 1000);
        Movie m1 = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        Movie m2 = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        
        cus.rentMovie(m1);
        cus.returnMovie(m2);
    }

    @Test
    public void customerReturnAMovie()
    {
        Customer cus = new Customer("Bianca", 1000);
        Movie m1 = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        cus.rentMovie(m1);
        cus.returnMovie(m1);
        assertEquals(0, cus.getRentedMovies().size());

    }

}

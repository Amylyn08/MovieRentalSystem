package moviestore;

import org.junit.Test;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;

import static org.junit.Assert.*;
import java.util.*;

public class MovieTest {
    @Test
    public void testMovieConstructor()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        assertEquals("Action-Adventure", m.getGenre());
        assertEquals(145, m.getDurationMins());
        assertEquals("An archeological expedition races against a rival group to uncover a lost city's secrets.", m.getSummary());
        assertEquals(4.36, m.getStarRating(), 0.0001);
    }

    @Test
    public void testMovieModifyStarRating()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.addRating(4.5);
        assertEquals(4.37, m.getStarRating(), 0.0001);
    }

    @Test
    public void testDigital()
    {
        DigitalMovie m = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        assertEquals(1000, m.getFileSize());
    }

    @Test
    public void testRentMovie()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.rentMovie();
        assertEquals(4, m.getStock());
    }

    @Test
    public void testReturnMovie()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.rentMovie();
        assertEquals(4, m.getStock());
    }

    @Test
    public void testEqualsDigitalvsDVD()
    {
        Movie dvd = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Movie digital = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        
        assertFalse(dvd.equals(digital));
    }

    @Test
    public void testEqualsDVD()
    {
        Movie digital = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        Movie digital2 = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        
        assertTrue(digital2.equals(digital));
    }

    @Test
    public void testEqualsDigital()
    {
        Movie digital = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        Movie digital2 = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        
        assertTrue(digital2.equals(digital));
    }

    @Test
    public void bookRentalSystemTestContainsTrue()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        Movie target = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);

        assertTrue(movies.contains(target));
        
    }

    @Test
    public void bookRentalSystemTestContainsFalse()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        Movie target = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);

        assertFalse(movies.contains(target));
        
    }

    @Test
    public void bookRentalSystemTestAddMovie()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        BookRentalSystem moviesToAddTo = new BookRentalSystem(movies);
        moviesToAddTo.addMovie(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        movies.add(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        assertTrue(movies.equals(moviesToAddTo.getMovies()));  
    }

    @Test (expected = IllegalArgumentException.class)
    public void bookRentalSystemTestAddMovieException()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        BookRentalSystem moviesToAddTo = new BookRentalSystem(movies);
        moviesToAddTo.addMovie(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

        movies.add(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));

    }

    @Test
    public void bookRentalSystemTestRentMovie()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        movies.add(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        BookRentalSystem moviesToRent = new BookRentalSystem(movies);
        moviesToRent.rentMovieStock(movies.get(0));
        assertEquals(4, moviesToRent.getMovies().get(0).getStock());
    }

    @Test (expected = IllegalArgumentException.class)
    public void bookRentalSystemTestRentMovieExceptionForStock()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 0));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        movies.add(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        BookRentalSystem moviesToRent = new BookRentalSystem(movies);
        moviesToRent.rentMovieStock(movies.get(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void bookRentalSystemTestRentMovieExceptionMissingMovie()
    {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
        movies.add(new DVD("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        movies.add(new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5));
        BookRentalSystem moviesToRent = new BookRentalSystem(movies);
        moviesToRent.rentMovieStock(new DigitalMovie("The Found City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5));
    }

}

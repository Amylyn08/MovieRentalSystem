package moviestore;
import moviestore.products.*;
import moviestore.display.FilterByAvailable;
import moviestore.display.FilterByGenre;
import moviestore.display.FilterByTitle;
import moviestore.display.IFilterBy;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.FileLoader;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

import org.junit.Test;

public class FilterbyTest {
    
    @Test
    public void checkFilterByTitle() throws LoaderFailedException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByTitle("The Lost City");
        List<Movie> filteredMovies = filter.filterMovies(movies);
        Movie movieDigital = new DigitalMovie("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50,1800, 2);
        Movie movieDVD = new DVD("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50, 3);

        assertTrue(movieDigital.equals(filteredMovies.get(0)));
        assertTrue(movieDVD.equals(filteredMovies.get(1)));

        assertEquals(2, filteredMovies.size());
    }

    @Test
    public void checkFilterByGenre() throws LoaderFailedException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByGenre("Action-Adventure");
        List<Movie> filteredMovies = filter.filterMovies( movies);
        
        Movie movieDigital = new DigitalMovie("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50,1800, 2);
        Movie movieDVD = new DVD("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50, 3);

        assertTrue(movieDigital.equals(filteredMovies.get(0)));
        assertTrue(movieDVD.equals(filteredMovies.get(1)));

        assertEquals(2, filteredMovies.size());
    }

    /** FIX */
    @Test
    public void checkFilterByAvailability() throws LoaderFailedException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByAvailable();
        List<Movie> filteredMovies = filter.filterMovies( movies);
        assertEquals(movies.size(), filteredMovies.size(), 0.0001);
    }
}

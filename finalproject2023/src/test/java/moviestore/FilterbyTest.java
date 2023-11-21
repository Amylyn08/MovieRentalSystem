package moviestore;
import moviestore.products.*;
import moviestore.display.FilterByAvailable;
import moviestore.display.FilterByGenre;
import moviestore.display.FilterByTitle;
import moviestore.display.IFilterBy;
import moviestore.loader.FileLoader;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

import org.junit.Test;

public class FilterbyTest {
    
    @Test
    public void checkFilterByTitle() throws IOException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByTitle();
        List<Movie> filteredMovies = filter.filterMovies("The Lost City", movies);
        Movie movieDigital = new DigitalMovie("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50,1800, 2);
        Movie movieDVD = new DVD("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50, 3);

        assertTrue(movieDigital.equals(filteredMovies.get(0)));
        assertTrue(movieDVD.equals(filteredMovies.get(1)));

        assertEquals(2, filteredMovies.size());
    }

    @Test
    public void checkFilterByGenre() throws IOException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByGenre();
        List<Movie> filteredMovies = filter.filterMovies("Action-Adventure", movies);
        
        Movie movieDigital = new DigitalMovie("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50,1800, 2);
        Movie movieDVD = new DVD("The Lost City", "Action-Adventure", 145, "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55, 40.50, 3);

        assertTrue(movieDigital.equals(filteredMovies.get(0)));
        assertTrue(movieDVD.equals(filteredMovies.get(1)));

        assertEquals(2, filteredMovies.size());
    }

    @Test
    public void checkFilterByAvailability() throws IOException{
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        IFilterBy filter = new FilterByAvailable();
        List<Movie> filteredMovies = filter.filterMovies("Infinite Horizons", movies);
        assertEquals(2, filteredMovies.size(), 0.0001);
    }
}

package moviestore;

import moviestore.products.*;
import moviestore.display.FilterByAvailable;
import moviestore.display.FilterByGenre;
import moviestore.display.FilterByTitle;
import moviestore.display.IFilterBy;
import moviestore.display.SortByTitle;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.FileLoader;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class FilterbyTest {

    @Test
    public void checkFilterByTitle() throws LoaderFailedException {
        MovieRentalSystem movies = new MovieRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByTitle());
        IFilterBy filter = new FilterByTitle("The Lost City");
        List<Movie> filteredMovies = filter.filterMovies(movies.getMovies());

        assertEquals(1, filteredMovies.size());
    }

    @Test
    public void checkFilterByGenre() throws LoaderFailedException {
        MovieRentalSystem movies = new MovieRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByTitle());
        IFilterBy filter = new FilterByGenre("Adventure");
        List<Movie> filteredMovies = filter.filterMovies(movies.getMovies());

        assertEquals(2, filteredMovies.size());
    }

    /** FIX */
    @Test
    public void checkFilterByAvailability() throws LoaderFailedException {
        MovieRentalSystem movies = new MovieRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
        movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                        "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                        240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
        movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                        "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                        230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
        movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                        "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                        12.99, 0, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
        IFilterBy filter = new FilterByAvailable();
        List<Movie> filteredMovies = filter.filterMovies(movies.getMovies());
        assertEquals(2, filteredMovies.size());
    }
}

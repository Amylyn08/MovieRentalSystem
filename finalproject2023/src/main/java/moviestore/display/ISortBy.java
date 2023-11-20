package moviestore.display;
import moviestore.products.*;
public interface ISortBy {
    /**
     * returns movies sorted by a specific criteria
     */
    int compareMovies(Movie a, Movie b);
}

package moviestore.display;
import moviestore.products.*;

/**
 * Responsible for sorting movies by tile
 */
public class SortByTitle implements ISortBy {
    /** 
     * Sorts movie using the compareTo for strings on the movies titles.
     * @param a - first movie.
     * @param b - second movie.
     * @return int corresponding to order of movies.
     */
    public int compareMovies(Movie a, Movie b)
    {
        return a.getTitle().compareTo(b.getTitle());
    }
}

package moviestore.display;
import moviestore.products.Movie;

/**
 * Responsible for sorting movies depending on price.
 */
public class SortByPrice implements ISortBy {

    
    /** 
     * Sets movies in increasing order focusing on movie prices
     * @param a - first move
     * @param b - second movie.
     * @return - int coresponding to how to order the movies.
     */
    public int compareMovies(Movie a, Movie b)
    {
        if (a.getPrice() > b.getPrice())
            return(1);
        else if (a.getPrice() < b.getPrice())
            return(-1);
        else
            return(0);
    }
}

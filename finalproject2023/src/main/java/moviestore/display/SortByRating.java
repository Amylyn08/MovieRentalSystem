package moviestore.display;
import moviestore.products.*;
/**
 * Responsible for sorting movie by the rating
 */
public class SortByRating implements ISortBy{
    
    /** 
     * Sorts movies dependin on the movies ratings.
     * @param a first movie
     * @param b second movie
     * @return - int corrensponding to order of movies.
     */
    public int compareMovies(Movie a, Movie b)
    {
        if (a.getStarRating() > b.getStarRating())
            return(1);
        else if (a.getStarRating() < b.getStarRating())
            return(-1);
        else
            return(0);
    }
}

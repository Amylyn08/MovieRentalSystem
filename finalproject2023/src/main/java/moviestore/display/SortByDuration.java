package moviestore.display;

import moviestore.products.Movie;

/**Class responsible for sorting movies with the durations in minutes. */
public class SortByDuration implements ISortBy 
{
    
    /** 
     * Sorts movie in increasing orders corresponding to the duration in minutes field
     * @param a 
     * @param b
     * @return int of -1, 1, or 0
     */
    public int compareMovies(Movie a, Movie b)
    {
        if (a.getDurationMins() > b.getDurationMins())
            return(1);
        else if (a.getDurationMins() < b.getDurationMins())
            return(-1);
        else
            return(0);
    }
}

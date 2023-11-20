package moviestore.display;

import moviestore.products.Movie;

public class SortByDuration implements ISortBy 
{
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

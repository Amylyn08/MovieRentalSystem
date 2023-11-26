package moviestore.display;
import moviestore.products.*;
public class SortByRating implements ISortBy{
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

package moviestore.display;
import moviestore.products.Movie;

public class SortByPrice implements ISortBy {
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

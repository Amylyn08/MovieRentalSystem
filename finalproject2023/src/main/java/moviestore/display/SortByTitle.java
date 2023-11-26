package moviestore.display;
import moviestore.products.*;

public class SortByTitle implements ISortBy {
    public int compareMovies(Movie a, Movie b)
    {
        return a.getTitle().compareTo(b.getTitle());
    }
}

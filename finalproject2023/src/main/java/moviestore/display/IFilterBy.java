package moviestore.display;
import moviestore.products.*;
import java.util.List;

public interface IFilterBy {
    /**
     * returns a list of movies properly filtered
     */
    List<Movie> filterMovies(String criteria);
}

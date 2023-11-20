package moviestore.display;
import java.util.*;

import moviestore.products.Movie;

public interface IDisplayer{

    /**
     * returns movies searched by whatever has been requested
     */
    public List<Movie> showMovies();
}

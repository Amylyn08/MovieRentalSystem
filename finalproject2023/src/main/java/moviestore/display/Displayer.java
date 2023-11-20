package moviestore.display;
import java.util.*;

import moviestore.products.Movie;

public abstract class Displayer{

    /**
     * returns movies searched by whatever has been requested
     */
    public List<Movie> showMovies(){
        throw new UnsupportedOperationException("Not written yet!");
    }

    /**
     * returns movies filtered by whatever has been requested
     */
    public List<Movie> filterMovies(){
        throw new UnsupportedOperationException("Not written yet!");
    }
}

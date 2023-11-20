package moviestore.loader;
import java.util.*;

import moviestore.products.Movie;

public interface IDatabase {
    List<Movie> loadMovies();
    // add or remove are considered P2 tasks
}

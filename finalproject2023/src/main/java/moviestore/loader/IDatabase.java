package moviestore.loader;
import java.util.*;
import java.io.*;

import moviestore.products.Movie;

public interface IDatabase {
    List<Movie> loadMovies() throws IOException;
    // add or remove are considered P2 tasks
}

package moviestore.loader;
import java.util.*;
import java.io.*;
import moviestore.exceptions.LoaderFailedException;

import moviestore.products.Movie;

public interface IDatabase {
    List<Movie> loadMovies() throws LoaderFailedException;
    // add or remove are considered P2 tasks
}

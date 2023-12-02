package moviestore.loader;

import moviestore.*;
import java.util.*;
import moviestore.exceptions.LoaderFailedException;

import moviestore.products.Movie;

public interface IDatabase {
    List<Movie> loadMovies() throws LoaderFailedException;

    // add or remove are considered P2 tasks
    List<Customer> loadCustomers() throws LoaderFailedException;
}

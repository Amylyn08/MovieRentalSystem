package moviestore;
import java.util.*;
import java.io.*;

public interface IDatabase {
    List<Movie> loadMovies() throws IOException;
    // add or remove are considered P2 tasks
}

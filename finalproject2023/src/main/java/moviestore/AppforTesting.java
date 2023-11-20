package moviestore;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class AppforTesting {
    public static void main(String[] args) throws IOException {
        FileLoader fl = new FileLoader();
        // System.out.println(fl.loadMovies());
        List<Movie> loadedMovies = fl.loadMovies();
        for (Movie movie : loadedMovies) {
            System.out.println(movie);
        }
    }
}

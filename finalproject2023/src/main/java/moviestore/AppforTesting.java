package moviestore;

import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.*;
import moviestore.products.*;
import java.util.*;
import java.sql.*;

public class AppforTesting {
    public static void main(String[] args) throws LoaderFailedException, SQLException {
        Scanner scan = new Scanner(System.in);
        FileLoader loader = new FileLoader();
        List<Movie> movies = loader.loadMovies();
        System.out.println("Press 1 to play trailer for movie");
        int input = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            movies.get(8).playTrailer();
        }
    }
}

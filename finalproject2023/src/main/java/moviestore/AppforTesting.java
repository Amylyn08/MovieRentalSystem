package moviestore;

import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.*;
import moviestore.products.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.sql.*;

public class AppforTesting {
    public static void main(String[] args) throws LoaderFailedException, SQLException {
        // SQLLoader loader = new SQLLoader("A2233420", "KimNamjoon2021");
        FileLoader loader = new FileLoader();
        // List<Movie> movies = loader.loadMovies();
        // for (Movie m : movies)
        // {
        // System.out.println(m);
        // }
        List<Customer> cus = loader.loadCustomers();
        for (Customer c : cus) {
            System.out.println(c);
        }
    }
}

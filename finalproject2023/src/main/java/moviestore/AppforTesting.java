package moviestore;

import moviestore.discounts.FiftyDollarDiscount;
import moviestore.discounts.IDiscountStrategy;
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
        FileLoader loader = new FileLoader();
        IDiscountStrategy discount = new FiftyDollarDiscount();
        List<Movie> movies = loader.loadMovies();
        Customer cus = new Customer("Tester customer", 120000);
        System.out.println(movies.get(33));

    }
}

package moviestore.loader;

import moviestore.Customer;
import moviestore.exceptions.LoaderFailedException;
import moviestore.products.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class FileLoader implements IDatabase {

    /**
     * Loads all movies from csv files containing Movies, DVDS, and Digitals, and
     * returns a list
     * of all movies together.
     * 
     * @return - returns a list of movies.
     * @throws LoaderFailedException - may throw an error if there is an
     *                               IOException.
     */
    public List<Movie> loadMovies() throws LoaderFailedException {
        try {
            Path pMovie = Paths.get("src\\CSVdata\\Movies.csv");
            Path pDvd = Paths.get("src\\CSVdata\\DVD.csv");
            Path pDigital = Paths.get("src\\CSVdata\\DigitalMovie.csv");
            List<String> linesMovie = Files.readAllLines(pMovie);
            List<String> linesDVD = Files.readAllLines(pDvd);
            List<String> linesDigital = Files.readAllLines(pDigital);
            List<Movie> loadMovies = new ArrayList<Movie>();
            for (int i = 0; i < linesMovie.size(); i++) {
                String[] dataMovie = linesMovie.get(i).split(",");
                String[] dataDigital = linesDigital.get(i).split(",");
                String[] dataDVD = linesDVD.get(i).split(",");
                loadMovies
                        .add(new DigitalMovie(dataMovie[1], dataMovie[2], Integer.parseInt(dataMovie[3]), dataMovie[4],
                                Double.parseDouble(dataMovie[5]), Integer.parseInt(dataMovie[6]),
                                Double.parseDouble(dataMovie[7]),
                                Integer.parseInt(dataDigital[1]), Integer.parseInt(dataDigital[2]), dataMovie[8]));

                loadMovies.add(new DVD(dataMovie[1], dataMovie[2], Integer.parseInt(dataMovie[3]), dataMovie[4],
                        Double.parseDouble(dataMovie[5]), Integer.parseInt(dataMovie[6]),
                        Double.parseDouble(dataMovie[7]),
                        Integer.parseInt(dataDVD[1]),  dataMovie[8]));
            }
            return loadMovies;
        } catch (IOException e) {
            throw new LoaderFailedException(e);
        }
    }

    /**
     * Creates a list of customers from customers.csv
     * 
     * @returns - a list of customers.
     * @throws LoaderFailedException - may throw an error if there is an
     *                               IOException.
     */
    public List<Customer> loadCustomers() throws LoaderFailedException {
        try {
            Path p = Paths.get("src\\CSVdata\\Customers.csv");
            List<String> customerLines = Files.readAllLines(p);
            List<Customer> loadedCustomers = new ArrayList<Customer>();
            for (String line : customerLines) {
                String[] data = line.split(",");
                loadedCustomers.add(new Customer(data[1], Integer.parseInt(data[0])));
            }
            return loadedCustomers;
        } catch (IOException e) {
            throw new LoaderFailedException(e);
        }
    }
}

package moviestore;

import java.util.*;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;
import moviestore.display.*;

public class BookRentalSystem {

    // make displayer field -- add later

    private List<Movie> movies;
    private List<Customer> customers;
    private ISortBy comparer;
    private IFilterBy filter;

    public BookRentalSystem(List<Movie> movies, List<Customer> customers) {
        this.movies = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m instanceof DVD) {
                this.movies.add(new DVD((DVD) m));
            } else {
                this.movies.add(new DigitalMovie((DigitalMovie) m));
            }
        }

        this.customers = new ArrayList<Customer>();
        for (Customer c : customers) {
            this.customers.add(new Customer(c));
        }
    }

    /**
     * this method allows the user to add customer into the system
     */
    public void addCustomer(Customer cusToAdd)
    {  
        if (this.customers.contains(cusToAdd))
        {
            throw new IllegalArgumentException("This customer exists!");
        }
        this.customers.add(cusToAdd);
    }

    /**
     * this method allows the user to remove customer into the system
     */
    public void removeCustomer(Customer cusToRemove)
    {
        this.customers.remove(cusToRemove);
    }

    public List<Customer> getCustomers() {
        return (this.customers);
    }

    public List<Movie> getMovies() {
        return (this.movies);
    }

    public void setSorting(ISortBy newMethod) {
        this.comparer = newMethod;
    }

    public void setFilter(IFilterBy newMethod) {
        this.filter = newMethod;
    }

    public IFilterBy getFilter() {
        return (this.filter);
    }

    public void selectionSort() {
        for (int i = 0; i < this.movies.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < this.movies.size(); j++) {
                if (this.comparer.compareMovies(movies.get(j), movies.get(index)) < 0) {
                    index = j;
                }
            }
            swap(i, index);
        }
    }

    /**
     * This method swaps two objects with two different indexes
     */
    private void swap(int i, int index) {
        Movie temp = this.movies.get(i);
        this.movies.set(i, this.movies.get(index));
        this.movies.set(index, temp);

    }

    public List<Movie> filterMovies(String criteria)/// AMYY*************************************
    {
        List<Movie> filteredMovies = new ArrayList<Movie>();
        /**
         * loop through movies
         * in filterBy: if movie.criteria = criteria
         * 
         */

        return filteredMovies;
    }

    /**
     * Lessens the stock when is called for a certain movie.
     */
    public void rentMovie(Movie m) {
        if (!this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).rentMovie();
    }

    /**
     * increments the stock when is called for a certain movie.
     */
    public void returnMovie(Movie m) {
        if (!this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).returnMovie();
    }

    /**
     * This method adds a movie to the list of movies.
     */
    public void addMovie(Movie m) {
        if (this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie already exists in the database!");
        }
        if (!(movies.contains(m))) {
            this.movies.add(m);
        }
    }

    public Movie findMovie(String title, String medium) {
        for (Movie m : this.movies) {
            if (medium == "digital" && m instanceof DigitalMovie && m.getTitle().toLowerCase().equals(title)) {
                return (m);
            } else if (medium == "dvd" && m instanceof DVD && m.getTitle().toLowerCase().equals(title)) {
                return (m);
            }
        }
        throw new IllegalArgumentException("this movie does not exist in our database!");
    }

}

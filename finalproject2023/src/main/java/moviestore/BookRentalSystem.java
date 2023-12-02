package moviestore;

import java.util.*;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;
import moviestore.display.*;

/**
 * This class is responsible for the logic of the application, and data manipulation of List objects
 */
public class BookRentalSystem {

    private List<Movie> movies;
    private List<Customer> customers;
    private ISortBy comparer;
    private IFilterBy filter;

    /**
     * Constructor to initialize lists of movies and customers.
     * @param movies - Reprenting movie object.
     * @param customers - Representing customer object.
     */
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

    
    /** 
     * Retrieves list of customers
     * @return List<Customer> 
     */
    public List<Customer> getCustomers() {
        return (this.customers);
    }

    
    /** 
     * Retrieves list of movies.
     * @return List<Movie>
     */
    public List<Movie> getMovies() {
        return (this.movies);
    }

    
    /** 
     * Sets the comparer field of the class, with an ISortBy type strategy.
     * @param newMethod
     */
    public void setSorting(ISortBy newMethod) {
        this.comparer = newMethod;
    }

    
    /** 
     * Sets the filter field with a IFilterBy type strategy
     * @param newMethod
     */
    public void setFilter(IFilterBy newMethod) {
        this.filter = newMethod;
    }

    
    /** 
     * Retrieves teh type of filter on the class.
     * @return IFilterBy - The filter strategry
     */
    public IFilterBy getFilter() {
        return (this.filter);
    }
    /**
     * function responsible for sorting by using the swap method,  using the comparer field.
     */
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
     * @param i 
     * @param j
     */
    private void swap(int i, int index) {
        Movie temp = this.movies.get(i);
        this.movies.set(i, this.movies.get(index));
        this.movies.set(index, temp);
    }

    /**
     * Lessens the stock when is called for a certain movie, calls rentMovie() from movie class.
     * @param m - The movie object that is getting rented
     */
    public void rentMovie(Movie m) {
        if (!this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).rentMovie();
    }

    /**
     * increments the stock when is called for a certain movie, calls returnMovie() from Movie class
     * @param m - the movie object that is being returned.
     */
    public void returnMovie(Movie m) {
        if (!this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).returnMovie();
    }

    /**
     * This method adds a movie to the list of movies.
     * @param m - the movie object that is getting added.
     * @throws IllegalArgumentException - If the movie already exists in the list of movies.
     */
    public void addMovie(Movie m) {
        if (this.movies.contains(m)) {
            throw new IllegalArgumentException("This movie already exists in the database!");
        }
        this.movies.add(m);
    }

    /**
     * this method removes a movie from the system!
     */
    public void removeMovie(Movie m)
    {
        this.movies.remove(m);
    }
    /**
     * Finds a movie in a list of movies.
     * @param title - Title of movie.
     * @param medium - Medium of the movie.
     * @throws IllegalArgumentException - If the movie does not exist in the database.
     * @return - The movie that is a match.
     */
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

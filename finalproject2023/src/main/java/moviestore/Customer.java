package moviestore;

import java.util.*;
import moviestore.products.Movie;
/**
 * The customer object with relevant customer attributes.
 */
public class Customer {
    private int points;
    private String name;
    private List<Movie> rented;

    /**
     * this method initializes the name and sets the points
     * 
     * @param {String} - name of customer
     * @param {points} - points of customer.
     */
    public Customer(String name, int points) {
        this.name = name;
        this.points = points;
        this.rented = new ArrayList<Movie>();
    }

    /**
     * copy constructor for Customer class
     * @other - the other customer object to copy it's attributes from.
     */
    public Customer(Customer other)
    {
        this(other.name, other.points);
    }
    /**
     * overrides the equals method, comparing the user name and points.
     * @param {o} - The object to compare the current Customer object to.
     * @return - boolean representing whether they are equal or not.
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Customer))
        {
            return false;
        }

        Customer other = (Customer) o;
        return(this.name.equals(other.name) && this.points == other.points);
    }

    /**
     * Retrieves name of customer.
     * @return - returns name of custoemr.
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the points of the customer
     * 
     * @return - points of customer.
     */
    public int getPoints() {
        return (this.points);
    }

    /**
     * this method returns the movies the customer has rented
     * @return List<Movie> - list of movies rented by customer.
     */
    public List<Movie> getRentedMovies() {
        return (this.rented);
    }

    /**
     * this method allows the customer to rent a movie
     * (adds points based on the price of the movie and adds the movie to their list
     * of rented movies)
     * @param {moveToRent} - The movie object to rent.
     * 
     */
    public void rentMovie(Movie movieToRent) {
        this.points += ((int) movieToRent.getPrice()) * 10;
        rented.add(movieToRent);
    }

    /**
     * this method returns a movie th customer has rented
     * 
     * @param rent - represents a movie they want to rent
     * @throws IllegalArgumentException - IF the movie was never rented.
     */
    public void returnMovie(Movie rent) {
        if (!this.rented.contains(rent)) {
            throw new IllegalArgumentException("this movie was never rented");
        }
        this.rented.remove(this.rented.indexOf(rent));
    }
    /**
     * Deducts point from customer.
     * @param points - points to deduct
     * @throws IllegalArgumentException - If the customer does not have enough points.
     */
    public void deductPoints(int points) {
        if (this.points < points) {
            throw new IllegalArgumentException("Customer does not have enough points!");
        }
        this.points -= points;
    }
    /**
     * Overrides the to string
     * @return - String with customer details.
     */
    @Override
    public String toString() {
        return "Customer name: " + this.name + ", Points: " + this.points;
    }

}

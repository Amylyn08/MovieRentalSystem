package moviestore;

import java.util.*;
import moviestore.products.Movie;

public class Customer {
    private int points;
    private String name;
    private List<Movie> rented;

    /**
     * this method initializes the name and sets the points as 0
     * 
     * @param {String} - name of customer
     */
    public Customer(String name, int points) {
        this.name = name;
        this.points = points;
        this.rented = new ArrayList<Movie>();
    }

    /**
     * copy constructor for Customer class
     */
    public Customer(Customer other)
    {
        this(other.name, other.points);
    }

    /**
     * this overloaded constructor is used to add a customer and initializes their points to 0.
     * @param {String} - represents the name of the customer
     */
    public Customer(String name)
    {
        this.name = name;
    }

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
     * @return - returns name
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the points of the customer
     * 
     * @return - points
     */
    public int getPoints() {
        return (this.points);
    }

    /**
     * this method returns the movies the customer has rented
     */
    public List<Movie> getRentedMovies() {
        return (this.rented);
    }

    /**
     * this method allows the customer to rent a movie
     * (adds points based on the price of the movie and adds the movie to their list
     * of rented movies)
     */
    public void rentMovie(Movie rent) {
        this.points += ((int) rent.getPrice()) * 10;
        rented.add(rent);
    }

    /**
     * this method returns a movie th customer has rented
     * 
     * @param rent - represents a movie they want to rent
     */
    public void returnMovie(Movie rent) {
        if (!this.rented.contains(rent)) {
            throw new IllegalArgumentException("this movie was never rented");
        }
        this.rented.remove(this.rented.indexOf(rent));
    }

    public void deductPoints(int points) {
        if (this.points < points) {
            throw new IllegalArgumentException("Customer does not have enough points!");
        }
        this.points -= points;
    }

    @Override
    public String toString() {
        return "Customer name: " + this.name + ", Points: " + this.points;
    }

}

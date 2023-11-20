package moviestore;
import java.util.*;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;

public class Customer {
    private int points;
    private String name;
    private List<Movie> rented;

    /**
     * this method initializes the name and sets the points as 0
     * @param {String} - name of customer
     */
    public Customer(String name, int points)
    {
        this.name = name;
        this.points = points;
        rented = new ArrayList<Movie>();
    }


    /**
     * @return - returns name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * gets the points of the customer
     * @return - points
     */
    public int getPoints()
    {
        return(this.points);
    }


    public List<Movie> getRentedMovies()
    {
        return(this.rented);
    }
    /**
     * this method allows the customer to rent a movie 
     * (adds points based on the price of the movie and adds the movie to their list of rented movies)
     * 
     * MODIFY TO USE DISCOUNT STRATEGY
     */
    public void rentMovie(Movie rent) //, IDiscountStrategy discountAmount)
    {
        this.points+= Math.round(rent.getPrice() * 100);
        if (rent instanceof DigitalMovie)
            rented.add(new DigitalMovie((DigitalMovie)rent));
        else if (rent instanceof DVD)
            rented.add(new DVD((DVD)rent));
    }

    /*
    private int usePointsForRental(Movie m)
    {
        int priceInPoints = (int) m.getPrice() * 1000;
        if (this.points < priceInPoints)
        {
            throw new IllegalArgumentException("not enough points. this action requires " + priceInPoints + " points to be valid.");
        }
        this.points = this.points - priceInPoints;
        return(priceInPoints);

    }*/

    /**
     * this method allows the customer to return the movie
     * and removes that movie from their list of rented movies
     */
    public void returnMovie(Movie rent)
    {
        if (!this.rented.contains(rent))
        {
            throw new IllegalArgumentException("this movie was never rented");
        }
        this.rented.remove(this.rented.indexOf(rent));
    }
}

package moviestore.products;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The `Movie` class represents a generic movie with various attributes,
 * with relevant methods to manipulate the atrributes of a Movie object  
 */
public class Movie {
    private String title;
    private String genre;
    private int durationMins;
    private String summary;
    private double starRating;
    private double additionOfRating;
    private int numRatings;
    private double price;
    private int stock;
    private String URL;

    /**
    * Constructs a new Movie object with the specified attributes.
    *
    * @param title The title of the movie.
    * @param genre The genre of the movie.
    * @param durationMins The duration of the movie in minutes.
    * @param summary A brief summary or description of the movie.
    * @param additionOfRating The total sum of ratings for the movie.
    * @param numRatings The number of ratings received by the movie.
    * @param price The price of the movie.
    * @param stock The current stock or inventory of the movie.
    * @param URL The URL or link associated with the movie.
    */
    public Movie(String title, String genre,
            int durationMins, String summary, double additionOfRating,
            int numRatings, double price, int stock, String URL) {
        this.title = title;
        this.genre = genre;
        this.durationMins = durationMins;
        this.summary = summary;
        this.starRating = Math.round(additionOfRating / numRatings * 100) / 100.0;
        this.additionOfRating = additionOfRating;
        this.numRatings = numRatings;
        this.price = price;
        this.stock = stock;
        this.URL = URL;
    }

    /**
     * this overloaded constructor allows the user to create a new movie and 
     * initializes fields dependent on customers to 0.
     */
    public Movie(String title, String genre,
    int durationMins, String summary, double price, int stock, String URL) 
    {
        if (price <= 0 || stock <=0 || durationMins <= 0)
        {
            throw new IllegalArgumentException("The price, stock, and duration cannot be less than or equal to 0!");
        }
        this.title = title;
        this.genre = genre;
        this.durationMins = durationMins;
        this.summary = summary;
        this.starRating = 0;
        this.additionOfRating = 0;
        this.numRatings = 0;
        this.price = price;
        this.stock = stock;
        this.URL = URL;
    }

    /**
     *Retrieve stock of a Movie object
     * @return - int representingn the stock available.
     */
    public int getStock() {
        return (this.stock);
    }

    /**
     * Retrieve title of movie. 
     * @return - returns String representing title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Retrieves price of Movie
     * @return - returns double presenting price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Retrieves sum of rating for a movie.
     * @return - returns double representing the addition of ratings.
     */
    public double getAdditionOfRating() {
        return this.additionOfRating;
    }

    /**
     *Retrieves the number for how many people have rated.
     * @return - returns int representing the number of ratings
     */
    public int getNumRatings() {
        return this.numRatings;
    }
    /**
     * Retrieves URL of the movie
     * @return - String representing the URL of the movie 
     */
    public String getURL() {
        return this.URL;
    }

    /**
     * Retrieves the genre of the movie 
     * @return - returns String representing genre
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Retrieves the duration in minutes of a movie object 
     * @return - returns int representing duration of the movie
     */
    public int getDurationMins() {
        return this.durationMins;
    }

    /**
     * Retrieves movie summary of movie object.
     * @return - returns String representing summary of the movie.
     */
    public String getSummary() {
        return this.summary;
    }

    /**
     * Retrieves the rating of a movie object.
     * @return - returns double representing the rating of the movie.
     */
    public double getStarRating() {
        return this.starRating;
    }

    /**
     * this method will add a rating to the movie and recalculate the rating, add to
     * additionOfRatings and add to numRatings
     * @param rating - the rating to be added to the movie object.
     * @throws IllegalArgumentException - If the rating being given is below 0.5 or above 5 stars. 
     */
    public void addRating(double rating) {
        if (rating > 5 || rating < 0.5) {
            throw new IllegalArgumentException("rating cannot be > 5 or < 0.5");
        }
        this.additionOfRating += rating;
        this.numRatings++;
        this.starRating = Math.round(this.additionOfRating / this.numRatings * 100) / 100.0;
    }

    /**
     * overrides toString
     *@return - String that holds relevant Movie information.
     */
    @Override
    public String toString() {
        return ("Movie Title: " + this.title + "\n Genre: " + this.genre + "\n Duration: " + this.durationMins
                + "\n Summary: " + this.summary + "\n Rating: " + this.starRating + "\n Stock: " + this.stock +
                "\n Price: "+this.price
                + "\n");

    }

    /**
     *Minuses 1 from the stock when a movie is renting
     *@throws IllegalArgumentException - when there isn't enough stock in the database.
     */
    public void rentMovie() {
        if (this.stock <= 0) {
            throw new IllegalArgumentException("This movie does not have enough stock in the database!");
        }
        this.stock = this.stock - 1;
    }

    /**
     *Adds 1 to the stock when a movie is returned/
     */
    public void returnMovie() {
        this.stock = this.stock + 1;
    }
    
    /**
     *Opens a page on user's default browser with the URL field of a Movie object 
     */
    public void playTrailer() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(this.URL));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unable to play move. Here is the URL: " + this.URL);
        }
    }

}

package moviestore.products;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class Movie {
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
     * constructor initializes the Movie fields
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

    public int getStock() {
        return (this.stock);
    }

    /**
     * @return - returns title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return - returns price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return - returns the addition of ratings
     */
    public double getAdditionOfRating() {
        return this.additionOfRating;
    }

    /**
     * @return - returns number of ratings
     */
    public int getNumRatings() {
        return this.numRatings;
    }

    public String getURL() {
        return this.URL;
    }

    /**
     * @return - returns genre
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * @return - returns duration of the movie
     */
    public int getDurationMins() {
        return this.durationMins;
    }

    /**
     * @return - returns summary of the movie
     */
    public String getSummary() {
        return this.summary;
    }

    /**
     * @return - returns the rating of the movie
     */
    public double getStarRating() {
        return this.starRating;
    }

    /**
     * this method will add a rating to the movie and recalculate the rating, add to
     * additionOfRatings and add to numRatings
     */
    public void addRating(double rating) {
        if (rating > 5 || rating < 0.5) {
            throw new IllegalArgumentException("rating cannot be > 5 or < 05");
        }
        this.additionOfRating += rating;
        this.numRatings++;
        this.starRating = Math.round(this.additionOfRating / this.numRatings * 100) / 100.0;
    }

    /**
     * overrides toString
     */
    @Override
    public String toString() {
        return ("Movie Title: " + this.title + "\n Genre: " + this.genre + "\n Duration: " + this.durationMins
                + "\n Summary: " + this.summary + "\n Rating: " + this.starRating + "\n Stock: " + this.stock
                + "\n");

    }

    public void rentMovie() {
        if (this.stock <= 0) {
            throw new IllegalArgumentException("This movie does not have enough stock in the database!");
        }
        this.stock = this.stock - 1;
    }

    public void returnMovie() {
        this.stock = this.stock + 1;
    }

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

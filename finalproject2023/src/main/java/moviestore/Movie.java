package moviestore;

import java.util.*;

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

    /**
     * constructor initializes the Movie fields
     */
    public Movie(String title, String genre,
            int durationMins, String summary, double additionOfRating,
            int numRatings, double price, int stock) {
        this.title = title;
        this.genre = genre;
        this.durationMins = durationMins;
        this.summary = summary;
        this.starRating = Math.round(additionOfRating / numRatings * 100) / 100.0;
        this.additionOfRating = additionOfRating;
        this.numRatings = numRatings;
        this.price = price;
        this.stock = stock;
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
        return ("Movie Title: " + this.title + ", Genre: " + this.genre + ", Duration: " + this.durationMins
                + ", Summary: " + this.summary + ", Rating: " + this.starRating + ", Stock: " + this.stock
                + "\n");

    }

    public void rentMovie() {
        this.stock = this.stock - 1;
    }

    public void returnMovie() {
        this.stock = this.stock + 1;
    }

}

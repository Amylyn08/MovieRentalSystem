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

    /**
     * constructor initializs the Movie fields
     */
    public Movie(String title, String genre, 
    int durationMins, String summary, double additionOfRating, 
    int numRatings, double price)
    {
        this.title = title;
        this.genre = genre; 
        this.durationMins = durationMins; 
        this.summary = summary;
        this.starRating = additionOfRating / numRatings; 
        this.additionOfRating = additionOfRating; 
        this.numRatings = numRatings;
        this.price = price;
    }

    /**
     * @return - returns title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return - returns title
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return - returns title
     */
    public double getAdditionOfRating() {
        return this.additionOfRating;
    }
    /**
     * @return - returns title
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
    public void addRating(double rating)
    {
        this.additionOfRating += rating;
        this.numRatings++;
        this.starRating = this.additionOfRating / this.numRatings;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Movie))
        {
            return(false);
        }

        Movie other = (Movie) o;
        return(this.title.equals(other.title));
    }
    
    /**
     * overrides toString
     */
    @Override
    public String toString()
    {
        return("Movie Title: " + this.title + ", Genre: " + this.genre + ", Duration: " + this.durationMins + ", Rating: " + this.starRating );

    }

    public abstract void rentMovie();

    public List<Movie> addMovie(List<Movie> movies)
    {
        if (!(movies.contains(this)))
        {
            movies.add(this);
        }
        return movies;
    }
}



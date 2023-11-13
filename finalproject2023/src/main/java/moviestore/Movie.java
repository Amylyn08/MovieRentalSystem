package moviestore;

public abstract class Movie {
    private String title;
    private String genre;
    private int durationMins;
    private String summary;
    private double starRating;
    private double additionOfRating;
    private int numRatings;

    /**
     * constructor initializs the Movie fields
     */
    public Movie(String title, String genre, 
    int durationMins, String summary, 
    double starRating, double additionOfRating, 
    int numRatings)
    {
        throw new UnsupportedOperationException("not written yet");
    }

    /**
     * @return - returns title
     */
    public String getTitle() {
        return this.title;
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
        throw new UnsupportedOperationException("not written yet");
    }
    
    /**
     * overrides toString
     */
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("not written yet");
    }

    /**
     * overrides .equals method
     */
    @Override
    public boolean equals(Object other)
    {
        throw new UnsupportedOperationException("not written yet");
    }
}

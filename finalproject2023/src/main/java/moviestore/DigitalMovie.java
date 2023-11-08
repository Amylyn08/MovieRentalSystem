package moviestore;

public class DigitalMovie extends Movie {
    private int fileSize;

    public DigitalMovie(
    String title, String genre, 
    int durationMins, String summary, 
    double starRating, double additionOfRating, 
    int numRatings, int fileSize)
    {
        super(title, genre, 
        durationMins, summary, 
        starRating, additionOfRating, 
        numRatings);
        this.fileSize = fileSize;
    }

    /**
     * overrides toString
     */
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("not written yet");
    }
}

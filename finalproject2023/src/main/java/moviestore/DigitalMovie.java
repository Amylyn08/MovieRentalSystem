package moviestore;

public class DigitalMovie extends Movie {
    private int fileSize;
    private int stock;

    public DigitalMovie(
    String title, String genre, 
    int durationMins, String summary, 
    double starRating, double additionOfRating, 
    int numRatings, double price, int fileSize)
    {
        super(title, genre, 
        durationMins, summary, additionOfRating, 
        numRatings, price);
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

    public void rentMovie()
    {
        this.stock = this.stock - 1;
    }
}

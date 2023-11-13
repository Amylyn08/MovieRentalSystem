package moviestore;

public class DigitalMovie extends Movie {
    private int fileSize;
    private int stock;

    public DigitalMovie(
    String title, String genre, 
    int durationMins, String summary,
    double additionOfRating, 
    int numRatings, double price, int fileSize, int stock)
    {
        super(title, genre, 
        durationMins, summary, additionOfRating, 
        numRatings, price);
        this.fileSize = fileSize;
        this.stock = stock;
    }

    public DigitalMovie(DigitalMovie m)
    {
        this(m.getTitle(), m.getGenre(), 
        m.getDurationMins(), m.getSummary(), 
        m.getAdditionOfRating(), 
        m.getNumRatings(), m.getPrice(), m.fileSize, m.stock);
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

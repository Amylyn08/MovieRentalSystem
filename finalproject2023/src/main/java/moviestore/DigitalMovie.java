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

    public int getStock()
    {
        return(this.stock);
    }

    public int getFileSize()
    {
        return(this.fileSize);
    }

    public void rentMovie()
    {
        this.stock = this.stock - 1;
    }

    public void returnMovie()
    {
        this.stock = this.stock + 1;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DigitalMovie))
        {
            return(false);
        }
        DigitalMovie other = (DigitalMovie) o;
        return(this.getTitle().equals(other.getTitle()));
    }
}

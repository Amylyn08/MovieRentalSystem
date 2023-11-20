package moviestore.products;

public class DVD extends Movie {

    public DVD(
    String title, String genre, 
    int durationMins, String summary, double additionOfRating, 
    int numRatings, double price, int stock)
    {
        super(title, genre, 
        durationMins, summary, additionOfRating, 
        numRatings, price, stock);
    }

    public DVD(DVD m)
    {
        this(m.getTitle(), m.getGenre(), 
        m.getDurationMins(), m.getSummary(), 
        m.getAdditionOfRating(), 
        m.getNumRatings(), m.getPrice(), m.getStock());
    }

    /**
     * overrides toString
     */
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("not written yet");
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DVD))
        {
            return(false);
        }
        DVD other = (DVD) o;
        return(this.getTitle().equals(other.getTitle()));
    }

}

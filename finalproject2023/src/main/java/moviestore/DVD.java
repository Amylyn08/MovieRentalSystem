package moviestore;

public class DVD extends Movie {

    private int stock;

    public DVD(
    String title, String genre, 
    int durationMins, String summary, 
    double starRating, double additionOfRating, 
    int numRatings, double price, int stock)
    {
        super(title, genre, 
        durationMins, summary, additionOfRating, 
        numRatings, price);
        this.stock = stock;
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

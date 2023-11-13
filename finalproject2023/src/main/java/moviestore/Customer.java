package moviestore;

public class Customer {
    private int points;
    private String name;

    /**
     * this method initializes the name and sets the points as 0
     * @param {String} - name of customer
     */
    public Customer(String name, int points)
    {
        this.name = name;
        this.points = points;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * gets the points of the customer
     * @return - points
     */
    public int getPoints()
    {
        throw new UnsupportedOperationException("not written yet");
    }

    /**
     * this method allows the customer to rent a movie 
     * (adds points based on the price of the movie)
     */
    public void rentMovie()
    {
        throw new UnsupportedOperationException("not written yet");
    }
}

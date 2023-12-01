package moviestore.products;

/**
 * This class is a representation of a physical Movie, which extends the functionality
 * of the Movie class.
 */
public class DVD extends Movie {

    /**
    * Constructs a new DVD object with the specified attributes.
    *
    * @param title The title of the DVD.
    * @param genre The genre of the DVD.
    * @param durationMins The duration of the DVD in minutes.
    * @param summary A brief summary or description of the DVD.
    * @param additionOfRating An additional rating value for the DVD.
    * @param numRatings The number of ratings received by the DVD.
    * @param price The price of the DVD.
    * @param stock The current stock or inventory of the DVD.
    * @param URL The URL or link associated with the DVD.
    */
    public DVD(
            String title, String genre,
            int durationMins, String summary, double additionOfRating,
            int numRatings, double price, int stock, String URL) {
        super(title, genre,
                durationMins, summary, additionOfRating,
                numRatings, price, stock, URL );
    }

    /**
     * this overloaded constructor is used to add a new movie into the system.
     */
    public DVD(
            String title, String genre,
            int durationMins, String summary, double price, int stock, String URL) {
        super(title, genre,
                durationMins, summary, price, stock, URL );
    }

    /**
     * Copy constructor 
     * @param m - The DVD Object that is getting copied from.
     */
    public DVD(DVD m) {
        this(m.getTitle(), m.getGenre(),
                m.getDurationMins(), m.getSummary(),
                m.getAdditionOfRating(),
                m.getNumRatings(), m.getPrice(), m.getStock(), m.getURL());
    }

    /**
     * overrides toString
     @return - String of the parent object
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Overrides the equals string to check if the titles are the same. 
     * @param o - The object that is getting compared with the current DVD object
     * @return - a boolean stating whether it is equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DVD)) {
            return (false);
        }
        DVD other = (DVD) o;
        return (this.getTitle().equals(other.getTitle()));
    }

}

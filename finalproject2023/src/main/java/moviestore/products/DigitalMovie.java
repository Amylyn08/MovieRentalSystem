package moviestore.products;

/**
 * DigitalMovie class representing the digital representation of a movie.
 * Extending class Movie.
 */
public class DigitalMovie extends Movie {
    private int fileSize;

    /**
    * Constructor for creating a new DigitalMovie object, inheriting attributes
    * from the parent Movie class and adding the specific file size attribute.
    * @param title The title of the digital movie.
    * @param genre The genre of the digital movie.
    * @param durationMins The duration of the digital movie in minutes.
    * @param summary A brief summary or description of the digital movie.
    * @param additionOfRating The total sum of ratings for the digital movie.
    * @param numRatings The number of ratings received by the digital movie.
    * @param price The price of the digital movie.
    * @param fileSize The size of the digital movie file.
    * @param stock The current stock or inventory of the digital movie.
    * @param URL The URL or link associated with the digital movie.
    */
    public DigitalMovie(
            String title, String genre,
            int durationMins, String summary,
            double additionOfRating,
            int numRatings, double price, int fileSize, int stock, String URL) {
        super(title, genre,
                durationMins, summary, additionOfRating,
                numRatings, price, stock, URL);
        this.fileSize = fileSize;
    }

    /**
     * Copies over attributes from a DigitalMovie object
     * @param m - the DigitalMovie object who's attributes are being used.
     */
    public DigitalMovie(DigitalMovie m) {
        this(m.getTitle(), m.getGenre(),
                m.getDurationMins(), m.getSummary(),
                m.getAdditionOfRating(),
                m.getNumRatings(), m.getPrice(), m.fileSize, m.getStock(), m.getURL());
    }

    /**
     * overrides toString
     * @retn - String wih toString from parent object + the file size of the digital movie object.
     */
    @Override
    public String toString() {
        return super.toString() + " File size: " + this.fileSize + "kB \n";
    }

    /**
     * Retrieves the file size.
     * @return - int representing the file size of a movie.
     */
    public int getFileSize() {
        return (this.fileSize);
    }

    /**
     * Overrides the equals method to compare titles
     * @param o - the object getting compared to the current DigitalMovie object.
     * @return - boolean stating whether or not the objects equal to eachother 
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DigitalMovie)) {
            return (false);
        }
        DigitalMovie other = (DigitalMovie) o;
        return (this.getTitle().equals(other.getTitle()));
    }
}

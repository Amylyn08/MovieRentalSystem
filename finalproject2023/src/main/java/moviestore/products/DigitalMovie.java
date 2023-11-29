package moviestore.products;

public class DigitalMovie extends Movie {
    private int fileSize;

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
     * this overloaded constructor is used to add a new movie into the system.
     */
    public DigitalMovie(
    String title, String genre,
    int durationMins, String summary, double price, int fileSize, int stock, String URL) {
        super(title, genre,
                durationMins, summary, price, stock, URL);
        if (fileSize <= 0)
                throw new IllegalArgumentException("filesize cannot be less than or equal to 0!");
        this.fileSize = fileSize;
    }

    public DigitalMovie(DigitalMovie m) {
        this(m.getTitle(), m.getGenre(),
                m.getDurationMins(), m.getSummary(),
                m.getAdditionOfRating(),
                m.getNumRatings(), m.getPrice(), m.fileSize, m.getStock(), m.getURL());
    }

    /**
     * overrides toString
     */
    @Override
    public String toString() {
        return super.toString() + " File size: " + this.fileSize + "kB \n";
    }

    public int getFileSize() {
        return (this.fileSize);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DigitalMovie)) {
            return (false);
        }
        DigitalMovie other = (DigitalMovie) o;
        return (this.getTitle().equals(other.getTitle()));
    }
}

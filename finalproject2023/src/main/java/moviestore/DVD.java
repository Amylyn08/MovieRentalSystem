package moviestore;

public class DVD extends Movie {

    private int stock;

    public DVD(
    String title, String genre, 
    int durationMins, String summary, 
    double starRating, double additionOfRating, 
    int numRatings)
    {
        super(title, genre, 
        durationMins, summary, 
        starRating, additionOfRating, 
        numRatings);
    }

}

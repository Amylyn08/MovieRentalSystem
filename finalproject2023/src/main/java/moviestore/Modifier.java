package moviestore;
import moviestore.products.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.*;
import java.util.*;
public class Modifier {

    /**
     * this method completely rewrites the movies into the csv file to save any new movies added
     * @param movies
     */
    public void addNewMovies(List<Movie> movies) throws IOException
    {
        List<String> csvFormatMovies = new ArrayList<String>();
        List<String> csvFormatDVD = new ArrayList<String>();
        List<String> csvFormatDigital = new ArrayList<String>();
        int index = 1;
        for (int i = 0; i < movies.size(); i+=2)
        {
            csvFormatMovies.add(index + "," + movies.get(i).getTitle() + 
            "," + movies.get(i).getGenre() + "," + movies.get(i).getDurationMins() + 
            "," + movies.get(i).getSummary() + "," + movies.get(i).getAdditionOfRating()  + "," + movies.get(i).getNumRatings()
            + "," + movies.get(i).getPrice()  + "," + movies.get(i).getURL());
            csvFormatDigital.add(index + "," + ((DigitalMovie) movies.get(i)).getFileSize() + "," +  movies.get(i).getStock());
            csvFormatDVD.add(index + "," + movies.get(i + 1).getStock());
            index++;
        }
        Files.write(Paths.get("src\\CSVdata\\Movies.csv"), csvFormatMovies);
        Files.write(Paths.get("src\\CSVdata\\DVD.csv"), csvFormatDVD);
        Files.write(Paths.get("src\\CSVdata\\DigitalMovie.csv"), csvFormatDigital);
    }

    /**
     * this method completely rewrites the customers into the csv file to save any new customers added
     * @param customers
     */
    public void addNewCustomers(List<Customer> customers) throws IOException
    {
        List<String> csvFormatCustomers = new ArrayList<String>();

        for (Customer cus : customers)
        {
            csvFormatCustomers.add(cus.getPoints() + "," + cus.getName());
        }
        Files.write(Paths.get("src\\CSVdata\\Customers.csv"), csvFormatCustomers);
    }
}

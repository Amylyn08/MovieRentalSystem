package moviestore;
import java.util.*;

public class BookRentalSystem{
    
    //make displayer field -- add later 
    
    private List<Movie> movies;

    public BookRentalSystem(List<Movie> movies)
    {
        this.movies = new ArrayList<Movie>();
        for(Movie m : movies)
        {
            if (m instanceof DVD)
            {
                this.movies.add(new DVD((DVD)m));
            }
            else
            {
                this.movies.add(new DigitalMovie((DigitalMovie)m));
            }
        }
    }

    public List<Movie> getMovies()
    {
        return(this.movies);
    }

    // addMovie method
    public void addMovie(Movie m)
    {
        if (this.movies.contains(m))
        {
            throw new IllegalArgumentException("This movie already exists in the database!");
        }
        if (!(movies.contains(m)))
        {
            this.movies.add(m);
        }
    }



}

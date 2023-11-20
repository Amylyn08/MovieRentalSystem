package moviestore;
import java.util.*;

import moviestore.display.Displayer;
import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;

public class BookRentalSystem{
    
    //make displayer field -- add later 
    
    private List<Movie> movies;
    private Displayer methodDisplay;

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

    public void setDisplayMethod(Displayer newMethod)
    {
        this.methodDisplay = newMethod;
    }

    public void rentMovieStock(Movie m)
    {
        if (!this.movies.contains(m))
        {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).rentMovie();
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

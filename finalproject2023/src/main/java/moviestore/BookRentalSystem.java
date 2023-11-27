package moviestore;
import java.util.*;

import moviestore.products.DVD;
import moviestore.products.DigitalMovie;
import moviestore.products.Movie;
import moviestore.display.*;

public class BookRentalSystem{
    
    //make displayer field -- add later 
    
    private List<Movie> movies;
    private ISortBy comparer;
    private IFilterBy filter;

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

    public void setSorting(ISortBy newMethod)
    {
        this.comparer = newMethod;
    }

    public void selectionSort()
    {
        for (int i = 0; i < this.movies.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < this.movies.size(); j++) {
                if(this.comparer.compareMovies(movies.get(j), movies.get(index)) < 0) {
                    index = j;
                }
            }
            swap(i, index);
        }
    }
    /**
     * This method swaps two objects with two different indexes
     */
    private void swap(int i, int index)
    {
        Movie temp = this.movies.get(i);
        this.movies.set(i, this.movies.get(index));
        this.movies.set(index, temp);

    }

    public List<Movie> filterMovies(String criteria)/// AMYY*************************************
    {
        List<Movie> filteredMovies = new ArrayList<Movie>();
        /**
         * loop through movies
         * in filterBy: if movie.criteria = criteria
         * 
         */

        return filteredMovies;
    }

    /**
     * Lessens the stock when is called for a certain movie.
     */
    public void rentMovieStock(Movie m)
    {
        if (!this.movies.contains(m))
        {
            throw new IllegalArgumentException("This movie does not exists in the database!");
        }

        this.movies.get(this.movies.indexOf(m)).rentMovie();
    }

    /**
     * This method adds a movie to the list of movies. 
     */
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

package moviestore.display;
import moviestore.products.*;
import java.util.*;

public class FilterByTitle implements IFilterBy{

    /**
     * Filters the movie by criteria i.e. title, and appends matching results into new list.
     * @param criteria - What the function is filtering by, i.e. title.
     * @param movies  - The list that the function is iterating through.
     * @return - returns the filtered list version of the movies.
     */
    @Override
    public List<Movie> filterMovies(String criteria, List<Movie> movies){
        List<Movie> moviesWithTitle = new ArrayList<Movie>();
        for(Movie m : movies){
            if(m.getTitle().contains(criteria)){
                moviesWithTitle.add(m);
            }       
        }
        return moviesWithTitle;
    }
}

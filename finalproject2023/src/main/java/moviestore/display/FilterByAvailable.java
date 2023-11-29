package moviestore.display;
import java.util.*;

import moviestore.products.Movie;

public class FilterByAvailable implements IFilterBy {

    /**
     * This function checks if the movie corresponding to the criteria, i.e. title, has enough stock.
     * @param movies - the list of movies the function is working in.
     * @return - returns the filtered list version of the movies.
     */
    @Override
    public List<Movie> filterMovies(List<Movie> movies){
        List<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie m : movies){
            if(m.getStock() > 0){
                filteredMovies.add(m);
            }
        }
        return filteredMovies;
    }

}

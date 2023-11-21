package moviestore.display;
import java.util.*;

import moviestore.products.Movie;

public class FilterByAvailable implements IFilterBy {

    @Override
    public List<Movie> filterMovies(String criteria, List<Movie> movies){
        List<Movie> filteredMovies = new ArrayList<Movie>();
        for(Movie m : movies){
            if(m.getTitle().contains(criteria) && m.getStock() > 0){
                filteredMovies.add(m);
            }
        }
        return filteredMovies;
    }

}

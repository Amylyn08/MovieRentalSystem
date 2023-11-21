package moviestore.display;

import java.util.*;

import moviestore.products.Movie;

public class FilterByGenre implements IFilterBy {

    @Override
    public List<Movie> filterMovies(String criteria, List<Movie> movies){
        List<Movie> filteredMovies = new ArrayList<Movie>();    
        for(Movie m : movies){
            if(m.getGenre().contains(criteria)){
                filteredMovies.add(m);
            }
        }
        return filteredMovies;
    }
}

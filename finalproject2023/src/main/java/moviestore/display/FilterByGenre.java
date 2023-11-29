package moviestore.display;

import java.util.*;

import moviestore.products.Movie;

public class FilterByGenre implements IFilterBy {

    private String criteria;

    public FilterByGenre(String criteria)
    {
        this.criteria = criteria;
    }
    /**
     * Filters the movie by criteria with genre, and appends matching results into new list.
     * @param criteria - What the function is filtering by, i.e. title.
     * @param movies  - The list that the function is iterating through.
     * @return - returns the filtered list version of the movies.
     */
    @Override
    public List<Movie> filterMovies(List<Movie> movies){
        List<Movie> filteredMovies = new ArrayList<Movie>();    
        for(Movie m : movies){
            if(m.getGenre().contains(this.criteria)){
                filteredMovies.add(m);
            }
        }
        return filteredMovies;
    }
}

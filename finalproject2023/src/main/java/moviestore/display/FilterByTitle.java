package moviestore.display;

import moviestore.products.*;
import java.util.*;

/** Class responsible in filtering by title */
public class FilterByTitle implements IFilterBy {

    private String criteria;

    /**
     * Constructor initializing the criteria field.
     * 
     * @param criteria - The criteria (title) needed to filte by.
     */
    public FilterByTitle(String criteria) {
        this.criteria = criteria;
    }

    /**
     * Filters the movie by criteria i.e. title, and appends matching results into
     * new list.
     * 
     * @param criteria - What the function is filtering by, i.e. title.
     * @param movies   - The list that the function is iterating through.
     * @return - returns the filtered list version of the movies.
     */
    @Override
    public List<Movie> filterMovies(List<Movie> movies) {
        List<Movie> moviesWithTitle = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(this.criteria.toLowerCase())) {
                moviesWithTitle.add(m);
            }
        }
        return moviesWithTitle;
    }
}

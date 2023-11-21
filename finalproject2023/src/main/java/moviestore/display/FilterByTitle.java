package moviestore.display;
import moviestore.products.*;
import java.util.*;

public class FilterByTitle implements IFilterBy{

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

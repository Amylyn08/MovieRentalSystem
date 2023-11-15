package moviestore;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class FileLoaderTest {

    @Test   
    public void testRightDVD() throws IOException{
        Movie movie1 = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",
        240, 55, 40.50, 1800);
        FileLoader loader = new FileLoader();
        List<Movie> loadedMovies = loader.loadMovies();
        // String firstMovie = loadedMovies.get(0).getTitle()
        assertTrue(movie1.equals(loadedMovies.get(0)));

    }
}

package moviestore;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FileLoaderTest {

    @Test
    public void testRightDigital() throws IOException {
        Movie digitalMovie = new DigitalMovie("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 1800, 2);
        FileLoader loader = new FileLoader();
        List<Movie> loadedMovies = loader.loadMovies();
        // String firstMovie = loadedMovies.get(0).getTitle()
        assertTrue(digitalMovie.equals(loadedMovies.get(0)));
    }

    @Test
    public void testRightDVD() throws IOException {
        Movie dvdMovie = new DVD("The Lost City", "Action-Adventure", 145,
                "An archeological expedition races against a rival group to uncover a lost city's secrets.", 240, 55,
                40.50, 3);
    }
}

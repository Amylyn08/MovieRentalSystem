package moviestore;

import org.junit.Test;
import static org.junit.Assert.*;

public class MovieTest {
    @Test
    public void testMovieConstructor()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        assertEquals("Action-Adventure", m.getGenre());
        assertEquals(145, m.getDurationMins());
        assertEquals("An archeological expedition races against a rival group to uncover a lost city's secrets.", m.getSummary());
        assertEquals(m.getAdditionOfRating()/m.getNumRatings(), m.getStarRating(), 0.0001);
    }

    @Test
    public void testMovieModifyStarRating()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.addRating(4.5);
        assertEquals(4.37, m.getStarRating(), 0.0001);
    }

    @Test
    public void testRentMovieDVD()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.addRating(4.5);
        assertEquals(4.37, m.getStarRating(), 0.0001);
    }

    @Test
    public void testDigital()
    {
        DigitalMovie m = new DigitalMovie("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 1000, 5);
        assertEquals(1000, m.getFileSize());
    }

    @Test
    public void testRentMovieDigital()
    {
        Movie m = new DVD("The Lost City","Action-Adventure",145,"An archeological expedition races against a rival group to uncover a lost city's secrets.",240,55,40.50, 5);
        m.rentMovie();
        assertEquals(4, m.getStock());
    }
}

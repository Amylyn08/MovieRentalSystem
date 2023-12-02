package moviestore;

import moviestore.products.*;
import java.util.ArrayList;
import java.util.List;
import moviestore.display.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortBy {
        @Test
        public void TestSortByTitle() {
                BookRentalSystem movies = new BookRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByTitle());

                List<Movie> finalSort = new ArrayList<Movie>();
                finalSort.add(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.selectionSort();
                assertEquals(finalSort, movies.getMovies());
        }

        @Test
        public void TestSortByPrice() {
                BookRentalSystem movies = new BookRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByPrice());

                List<Movie> finalSort = new ArrayList<Movie>();
                finalSort.add(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.selectionSort();
                assertEquals(finalSort, movies.getMovies());
        }

        @Test
        public void TestSortByRating() {
                BookRentalSystem movies = new BookRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByRating());

                List<Movie> finalSort = new ArrayList<Movie>();
                finalSort.add(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.selectionSort();
                assertEquals(finalSort, movies.getMovies());
        }

        @Test
        public void TestSortByDuration() {
                BookRentalSystem movies = new BookRentalSystem(new ArrayList<Movie>(), new ArrayList<Customer>());
                movies.addMovie(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.addMovie(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.setSorting(new SortByDuration());

                List<Movie> finalSort = new ArrayList<Movie>();
                finalSort.add(new DigitalMovie("The Cosmic Heist", "Sci-Fi", 140,
                                "A group of intergalactic thieves plans an audacious heist to steal a cosmic artifact.",
                                230, 52, 32.99, 1000, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DVD("The Lost City", "Action-Adventure", 145,
                                "An archeological expedition races against a rival group to uncover a lost city's secrets.",
                                240, 55, 40.50, 5, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                finalSort.add(new DVD("Flight of Fantasy", "Adventure", 155,
                                "Embark on a fantastical journey with mythical creatures and epic adventures.", 235, 53,
                                12.99, 8, "https://www.youtube.com/watch?v=nfKO9rYDmE8"));
                movies.selectionSort();
                assertEquals(finalSort, movies.getMovies());
        }
}

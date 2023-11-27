package moviestore;
import moviestore.products.*;
import moviestore.display.*;
import moviestore.exceptions.*;
import moviestore.loader.*;
import java.util.*;
public class Employee {
    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        IDatabase loader = new FileLoader();
        try {
            List<Movie> movies = loader.loadMovies();
            BookRentalSystem movieSystem = new BookRentalSystem(movies);
            mainMenu(movieSystem);
        }
        catch (LoaderFailedException e)
        {
            System.out.println("movies could not be properly loaded");
        }

    }

    public static void printMovies(List<Movie> movies)
    {
        for (Movie m : movies){
            System.out.println(m);
        }
    }

    /**
     * this function represents the main menu with MAIN FUNCTIONS
     */
    public static void mainMenu(BookRentalSystem movies) {
        int input = 0;
        System.out.println(
                "\n ------------------------------------------------------------- \n Here are your options.. choose wisely. ");
        System.out.println("1. View all movies");
        System.out.println("2. View movies sorted by a criteria");
        System.out.println("3. View movies filtered by a criteria");
        System.out.println("4. Add or modify");
        System.out.println("5. EXIT \n");
        System.out.println("Enter the number of the option you would like to select: ");
        do {

            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    printMovies(movies.getMovies());
                    mainMenu(movies);
                    break;
                case 2:
                    System.out.println("\033c");
                    viewSortedMovies(movies);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Chosen \"EXIT\". System exiting.. Goodbye!!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (true);

    }

    public static void sortedMenuOptions()
    {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. View movies sorted by duration");
        System.out.println("2. View movies sorted by price");
        System.out.println("3. View movies sorted by rating");
        System.out.println("4. View movies sorted by title");
        System.out.println("5. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * The menu of customer related viewing options.
     * Takes in user input to chose option.
     */
    public static void viewSortedMovies(BookRentalSystem movies) {
        int input = 0;
        sortedMenuOptions();
        do {
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    System.out.println("\033c");
                    movies.setSorting(new SortByDuration());
                    break;
                case 2:
                    System.out.println("\033c");
                    movies.setSorting(new SortByPrice());
                    break;
                case 3:
                    System.out.println("\033c");
                    movies.setSorting(new SortByRating());
                    break;
                case 4:
                    System.out.println("\033c");
                    movies.setSorting(new SortByTitle());
                    break;
                case 5:
                    System.out.println("\033c");
                    mainMenu(movies);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            movies.selectionSort();
            printMovies(movies.getMovies());
            sortedMenuOptions();
        } while (true);
    }

    public static void filteredMenuOptions()
    {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. View movies sorted by duration");
        System.out.println("2. View movies sorted by price");
        System.out.println("3. View movies sorted by rating");
        System.out.println("4. View movies sorted by title");
        System.out.println("5. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * The menu of customer related viewing options.
     * Takes in user input to chose option.
     */
    public static void viewFilteredMovies(BookRentalSystem movies) {
        int input = 0;
        sortedMenuOptions();
        do {
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    System.out.println("\033c");
                    movies.setSorting(new SortByDuration());
                    break;
                case 2:
                    System.out.println("\033c");
                    movies.setSorting(new SortByPrice());
                    break;
                case 3:
                    System.out.println("\033c");
                    movies.setSorting(new SortByRating());
                    break;
                case 4:
                    System.out.println("\033c");
                    movies.setSorting(new SortByTitle());
                    break;
                case 5:
                    System.out.println("\033c");
                    mainMenu(movies);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            movies.selectionSort();
            printMovies(movies.getMovies());
            sortedMenuOptions();
        } while (true);
    }
}

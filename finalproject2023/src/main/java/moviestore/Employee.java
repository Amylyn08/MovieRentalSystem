package moviestore;
import moviestore.products.*;
import moviestore.display.*;
import moviestore.exceptions.*;
import moviestore.loader.*;
import moviestore.discounts.*;
import java.util.*;
public class Employee {
    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        IDatabase loader = new FileLoader();
        try {
            List<Movie> movies = loader.loadMovies();
            List<Customer> customers = loader.loadCustomers();
            BookRentalSystem movieSystem = new BookRentalSystem(movies);
            mainMenu(movieSystem, customers);
        }
        catch (LoaderFailedException e)
        {
            System.out.println("movies could not be properly loaded");
        }
    }

    public static void printMovies(List<Movie> movies)
    {
        int index = 1;
        for (Movie m : movies){
            System.out.println(index + "" + m);
            index++;
        }
        if (movies.size() == 0)
            System.out.println("No movies available!");
    }

    /**
     * this function represents the main menu with MAIN FUNCTIONS
     */
    public static void mainMenu(BookRentalSystem movies, List<Customer> customers) {
        int input = 0;
        System.out.println(
                "\n ------------------------------------------------------------- \n Here are your options.. choose wisely. ");
        System.out.println("1. View all movies");
        System.out.println("2. View movies sorted by a criteria");
        System.out.println("3. View movies filtered by a criteria");
        System.out.println("4. Manage customers");
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
                    mainMenu(movies, customers);
                    break;
                case 2:
                    System.out.println("\033c");
                    viewSortedMovies(movies, customers);
                    break;
                case 3:
                    System.out.println("\033c");
                    viewFilteredMovies(movies, customers);
                    break;
                case 4:
                    manageCustomers(movies, customers);
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
    public static void viewSortedMovies(BookRentalSystem movies, List<Customer> customers) {
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
                    mainMenu(movies, customers);
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
    public static void viewFilteredMovies(BookRentalSystem movies, List<Customer> customers) {
        int input = 0;
        filteredMenuOptions();
        do {
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    System.out.println("\033c");
                    System.out.println("The movies that have enough stock are:");
                    movies.setFilter(new FilterByAvailable());
                    break;
                case 2:
                    System.out.println("\033c");
                    System.out.println("Enter the genre: ");
                    String genre = scan.nextLine(); 
                    movies.setFilter(new FilterByGenre(genre));
                    break;
                case 3:
                    System.out.println("\033c");
                    System.out.println("Enter the title snippet: ");
                    String titleSnip = scan.nextLine(); 
                    movies.setFilter(new FilterByTitle(titleSnip));
                    break;
                case 4:
                    System.out.println("\033c");
                    mainMenu(movies, customers);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            List<Movie> filtered = movies.getFilter().filterMovies(movies.getMovies());
            printMovies(filtered);
            filteredMenuOptions();
        } while (true);
    }

    public static void customerMenuOptions()
    {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. Rent movie");
        System.out.println("2. Return movie");
        System.out.println("3. Select a new customer");
        System.out.println("4. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    public static Customer getCustomer(List<Customer> customers)
    {
        Customer selected = null;
        while (selected == null)
        {
            System.out.println("Enter the customer's full name: ");
            String name = scan.nextLine();
            for (Customer c : customers){
                if (c.getName().contains(name))
                {
                    selected = c;
                }
            }
        }
        return(selected);
    }

    /** 
     * this method will allow an employee to select a movie the customer can rent. 
     * this method already includes validation for stock and if the movie exists in the DB
     */
    public static Movie getMovie(BookRentalSystem movies)
    {
        System.out.println("The movies that have enough stock are:");
        movies.setFilter(new FilterByAvailable());
        List<Movie> filtered = movies.getFilter().filterMovies(movies.getMovies());
        printMovies(filtered);
        System.out.println("Please select the number of the movie you would like to select");
        int input = 0;
        while (true)
        {
            try {
                input = Integer.parseInt(scan.nextLine());
                return filtered.get(input - 1);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Please select a valid number");
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter a number");
            }
        }
        
    }

    // public static void rentMovieValidation(Customer selected, BookRentalSystem movies)
    // {
    //     boolean successful = false;
    //     while (!successful)
    //     {
    //         try {
    //             Movie m = getMovie(movies);
    //             movies.rentMovie(m);
    //             selected.rentMovie(m);
    //             System.out.println("movie had been rented successfully!");

    //             successful = true;
    //         }
    //         catch(IllegalArgumentException e)
    //         {
    //             System.out.println(e.getMessage());
    //         }
    //     }
    // }

    public static double payRent(Movie m, Customer c)
    {
        int minPoints = 10000;
        IDiscountStrategy[] discounts = {new FiveDollarDiscount(), new TenDollarDiscount(), new TwentyDollarDiscount(), new FiftyDollarDiscount()};
        if (c.getPoints() < minPoints)
            return m.getPrice();
        int input = 0;
        while (true)
        {
            try {
                System.out.println("Enter the discount you would like to use: \n 1) 5$ \n 2) 10$ \n 3) 20$ \n 4) 50$");
                input = Integer.parseInt(scan.nextLine());
                return discounts[input].finalPrice(c, m);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Please select a valid number");
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter an integer");
            }
        }

    }

    public static Movie movieToReturn(Customer c)
    {
        List<Movie> cusMovies = c.getRentedMovies();
        System.out.println("Select the movie you would like to return");
        printMovies(cusMovies);
        int input = 0;
        while (true)
        {
            try {
                input = Integer.parseInt(scan.nextLine());
                return cusMovies.get(input - 1);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Please select a valid number");
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter an integer");
            }
        }
    }

    /**
     * The menu of customer related viewing options.
     * Takes in user input to chose option.
     */
    public static void manageCustomers(BookRentalSystem movies, List<Customer> customers) {
        int input = 0;
        Customer selected = getCustomer(customers);
        customerMenuOptions();
        do {
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    System.out.println("\033c");
                    Movie toRent = getMovie(movies);
                    System.out.println("Would you like to use your points? y/n");
                    String ans = "";
                    do {
                        ans = scan.nextLine();
                    } while (ans != "y" || ans != "n");
                    if (ans == "y")
                    {
                        System.out.println("Price after discount is: " + payRent(toRent, selected) + "$");
                    }
                    movies.rentMovie(toRent);
                    selected.rentMovie(toRent);
                    System.out.println("Movie rented successfully");
                    break;
                case 2:
                    System.out.println("\033c");
                    if (selected.getRentedMovies().size() == 0)
                        System.out.println("nothing to return");
                    else
                    {
                        Movie toReturn = movieToReturn(selected);
                        movies.returnMovie(toReturn);
                        selected.returnMovie(toReturn);
                        System.out.println("Movie returned successfully");
                    }
                    break;
                case 3:
                    System.out.println("\033c");
                    selected = getCustomer(customers);
                    break;
                case 4:
                    System.out.println("\033c");
                    mainMenu(movies, customers);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            customerMenuOptions();
        } while (true);
    }

}

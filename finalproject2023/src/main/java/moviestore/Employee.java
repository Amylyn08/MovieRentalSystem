package moviestore;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import moviestore.discounts.FiftyDollarDiscount;
import moviestore.discounts.FiveDollarDiscount;
import moviestore.discounts.IDiscountStrategy;
import moviestore.discounts.TenDollarDiscount;
import moviestore.discounts.TwentyDollarDiscount;
import moviestore.display.FilterByAvailable;
import moviestore.display.FilterByGenre;
import moviestore.display.FilterByTitle;
import moviestore.display.SortByDuration;
import moviestore.display.SortByPrice;
import moviestore.display.SortByRating;
import moviestore.display.SortByTitle;
import moviestore.exceptions.LoaderFailedException;
import moviestore.loader.FileLoader;
import moviestore.loader.IDatabase;
import moviestore.products.Movie;
public class Employee {
    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        IDatabase loader = new FileLoader();
        try {
            List<Movie> movies = loader.loadMovies();
            List<Customer> customers = loader.loadCustomers();
            BookRentalSystem movieSystem = new BookRentalSystem(movies, customers);
            mainMenu(movieSystem);
        }
        catch (LoaderFailedException e)
        {
            System.out.println("movies could not be properly loaded");
        }
    }

    /**
     * this function represents the main menu with MAIN FUNCTIONS
     */
    public static void mainMenu(BookRentalSystem system) {
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
                    printMovies(system.getMovies());
                    mainMenu(system);
                    break;
                case 2:
                    System.out.println("\033c");
                    viewSortedMovies(system);
                    break;
                case 3:
                    System.out.println("\033c");
                    viewFilteredMovies(system);
                    break;
                case 4:
                    manageCustomers(system);
                    break;
                case 5:
                    System.out.println("Chosen \"EXIT\". System exiting.. Goodbye!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (true);

    }

    /**
     * this function displays the menu options for sorting
     */
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
     * this method allows the user to interact with the sorting menu by taking their input corresponding
     * to the number of the option they chose
     */
    public static void viewSortedMovies(BookRentalSystem system) {
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
                    system.setSorting(new SortByDuration());
                    break;
                case 2:
                    System.out.println("\033c");
                    system.setSorting(new SortByPrice());
                    break;
                case 3:
                    System.out.println("\033c");
                    system.setSorting(new SortByRating());
                    break;
                case 4:
                    System.out.println("\033c");
                    system.setSorting(new SortByTitle());
                    break;
                case 5:
                    System.out.println("\033c");
                    mainMenu(system);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            system.selectionSort();
            printMovies(system.getMovies());
            sortedMenuOptions();
        } while (true);
    }

    /**
     * this function displays the menu options for filtering
     */
    public static void filteredMenuOptions()
    {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. View movies fltered by available");
        System.out.println("2. View movies sorted by genre");
        System.out.println("3. View movies sorted by title");
        System.out.println("4. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * this method allows the user to interact with the sorting menu by taking their input corresponding
     * to the number of the option they chose
     */
    public static void viewFilteredMovies(BookRentalSystem system) {
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
                    system.setFilter(new FilterByAvailable());
                    break;
                case 2:
                    System.out.println("\033c");
                    System.out.println("Enter the genre: ");
                    String genre = scan.nextLine(); 
                    system.setFilter(new FilterByGenre(genre));
                    break;
                case 3:
                    System.out.println("\033c");
                    System.out.println("Enter the title snippet: ");
                    String titleSnip = scan.nextLine(); 
                    system.setFilter(new FilterByTitle(titleSnip));
                    break;
                case 4:
                    System.out.println("\033c");
                    mainMenu(system);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            List<Movie> filtered = system.getFilter().filterMovies(system.getMovies());
            printMovies(filtered);
            filteredMenuOptions();
        } while (true);
    }

    /**
     * this function displays the menu options for filtering
     */
    public static void customerMenuOptions()
    {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. Rent movie");
        System.out.println("2. Return movie");
        System.out.println("3. Select a new customer");
        System.out.println("4. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * this method allows the user to interact with the customer menu by taking their input corresponding
     * to the number of the option they chose
     */
    public static void manageCustomers(BookRentalSystem system) {
        int input = 0;
        Customer selected = getCustomer(system.getCustomers());
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
                    Movie toRent = getMovie(system);
                    System.out.println("Would you like to use your points? y/n");
                    String ans = "";
                    do {
                        ans = scan.nextLine();
                    } while (!ans.equals("y") && !ans.equals("n"));
                    if (ans.equals("y"))
                    {
                        System.out.println("Price after discount is: " + payRent(toRent, selected) + "$");
                    }
                    system.rentMovie(toRent);
                    selected.rentMovie(toRent);
                    System.out.println("Movie rented successfully");
                    break;
                case 2:
                    System.out.println("\033c");
                    if (selected.getRentedMovies().size() == 0)
                        System.out.println("Nothing to return!");
                    else
                    {
                        Movie toReturn = movieToReturn(selected);
                        system.returnMovie(toReturn);
                        selected.returnMovie(toReturn);
                        System.out.println("Movie returned successfully");
                    }
                    break;
                case 3:
                    System.out.println("\033c");
                    selected = getCustomer(system.getCustomers());
                    break;
                case 4:
                    System.out.println("\033c");
                    mainMenu(system);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            customerMenuOptions();
        } while (true);  
    }

    /********************************************** HELPER METHODS ******************************************/
    
    public static void printMovies(List<Movie> movies)
    {
        int index = 1;
        for (Movie m : movies){
            System.out.println(index + ". " + m);
            index++;
        }
        if (movies.size() == 0)
            System.out.println("No movies available!");
    }

    public static void printCustomers(List<Customer> cus)
    {
        int index = 1;
        for (Customer c : cus)
        {
            System.out.println(index + ". " + c);
            index++;
        }
        if (cus.size() == 0)
            System.out.println("No customers available!");
    }

    /**
     * this method allows the employee to select which customer 
     * they would like to manage
     * @param {BookRentalSystem} - represents the whole system
     * @return -  the movie to be returned
     */
    public static Customer getCustomer(List<Customer> customers)
    {
        System.out.println("Select the customer you would like to manage");
        printCustomers(customers);
        int input = 0;
        while(true)
        {
            try {
                input = Integer.parseInt(scan.nextLine());
                return customers.get(input - 1);
            }
            catch (IndexOutOfBoundsException e)
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
     * this method will allow an employee to select a movie the customer can rent by allowing 
     * them to select from ONLY AVAILABLE MOVIES .
     * this method already includes validation for stock and if the movie exists in the DB 
     * due to the use of the filter.
     * @param {BookRentalSystem} - represents the whole system
     * @return -  the movie to be returned
     */
    public static Movie getMovie(BookRentalSystem system)
    {
        System.out.println("The movies that have enough stock are:");
        system.setFilter(new FilterByAvailable());
        List<Movie> filtered = system.getFilter().filterMovies(system.getMovies());
        printMovies(filtered);
        System.out.println("Please select the number of the movie you would like to select");
        int input = 0;
        while (true)
        {
            try {
                input = Integer.parseInt(scan.nextLine());
                return filtered.get(input - 1);
            }
            catch (IndexOutOfBoundsException e)
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

    /**
     * this method asks the customer which points discount they would like to use (if any)
     * and returns the final price after the discount has been applied
     * @param {Movie} - represents the movie being managed
     * @param {Customer} - represents the customer being managed
     * @return -  the final price after discount
     */
    public static double payRent(Movie m, Customer c)
    {
        int MINPOINTS = 10000;
        IDiscountStrategy[] discounts = {new FiveDollarDiscount(), new TenDollarDiscount(), new TwentyDollarDiscount(), new FiftyDollarDiscount()};
        if (c.getPoints() < MINPOINTS)
            return m.getPrice();
        int input = 0;
        while (true)
        {
            try {
                System.out.println("Enter the discount you would like to use: \n 1) 5$ \n 2) 10$ \n 3) 20$ \n 4) 50$");
                input = Integer.parseInt(scan.nextLine());
                return discounts[input].finalPrice(c, m);
            }
            catch (IndexOutOfBoundsException e)
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
     * this method allows the employee to select which of the customer's movies will
     * be returned.
     * @param {Customer} - represents the customer being managed
     * @return -  the movie to be returned
     */
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
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Please select a valid number");
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter an integer");
            }
        }
    }

}

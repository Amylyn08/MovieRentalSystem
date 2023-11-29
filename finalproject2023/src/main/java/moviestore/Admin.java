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
import moviestore.products.*;

public class Admin {
    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        IDatabase loader = new FileLoader();
        try {
            List<Movie> movies = loader.loadMovies();
            List<Customer> customers = loader.loadCustomers();
            BookRentalSystem movieSystem = new BookRentalSystem(movies, customers);
            mainMenu(movieSystem);
        } catch (LoaderFailedException e) {
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
                    playTrailer(system);
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
                    manageSystem(system);
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
    public static void sortedMenuOptions() {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. View movies sorted by duration");
        System.out.println("2. View movies sorted by price");
        System.out.println("3. View movies sorted by rating");
        System.out.println("4. View movies sorted by title");
        System.out.println("5. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * this method allows the user to interact with the sorting menu by taking their
     * input corresponding
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
    public static void filteredMenuOptions() {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. View movies fltered by available");
        System.out.println("2. View movies sorted by genre");
        System.out.println("3. View movies sorted by title");
        System.out.println("4. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * this method allows the user to interact with the sorting menu by taking their
     * input corresponding
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
     * this function displays the menu options for managing the system
     */
    public static void manageSystemOptions() {
        System.out.println("\n------------------------------------------------------------- ");
        System.out.println("1. Remove a customer");
        System.out.println("2. Add a customer");
        System.out.println("3. Remove a movie");
        System.out.println("3. Add a movie");
        System.out.println("4. BACK \n");
        System.out.println("Enter the number of the option you would like to select: ");
    }

    /**
     * this method allows the user to interact with the system managing menu by taking their
     * input corresponding
     * to the number of the option they chose
     */
    public static void manageSystem(BookRentalSystem system) {
        int input = 0;
        manageSystemOptions();
        do {
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                input = 0;
            }
            switch (input) {
                case 1:
                    System.out.println("\033c");
                    Customer cusToRemove = getCustomerFromSystem(system.getCustomers());
                    system.removeCustomer(cusToRemove);
                    break;
                case 2:
                    System.out.println("\033c");
                    addCustomerToSystem(system);
                    
                    break;
                case 3:
                    System.out.println("\033c");
                    Movie movieToRemove = getMovieFromSystem(system);
                    system.removeMovie(movieToRemove);
                    break;
                case 4:
                    System.out.println("\033c");
                    Movie toAdd = createMovie();
                    try { 
                        system.addMovie(toAdd); 
                    } catch (IllegalArgumentException e) { 
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
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

    /**********************************************
     * HELPER METHODS
     ******************************************/

     /**
      * this method will print all movies
      * @param {List<Movie>} - represents the movies to print
      */
    public static void printMovies(List<Movie> movies) {
        int index = 1;
        for (Movie m : movies) {
            System.out.println(index + ". " + m);
            index++;
        }
        if (movies.size() == 0)
            System.out.println("No movies available!");
    }

    /**
     * this method will print all customers
     * @param {List<Customer>} - represents the list of customers to print 
     */
    public static void printCustomers(List<Customer> cus) {
        int index = 1;
        for (Customer c : cus) {
            System.out.println(index + ". " + c);
            index++;
        }
        if (cus.size() == 0)
            System.out.println("No customers available!");
    }

    /**
     * this method allows the admin to create a new customer
     * and add that customer to the system.
     * @param {BookRentalSystem} - represents the system
     */
    public static void addCustomerToSystem(BookRentalSystem system)
    {
        System.out.println("Please enter the name of the customer:");
        Customer cusToAdd = new Customer(scan.nextLine());
        try {
            system.addCustomer(cusToAdd);
            System.out.println("Customer added successfully!");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * this method allows the employee to select which customer
     * they would like to manage
     * 
     * @param {BookRentalSystem} - represents the whole system
     * @return - the movie to be returned
     */
    public static Customer getCustomerFromSystem(List<Customer> customers) {
        System.out.println("Select the customer you would like to manage");
        printCustomers(customers);
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return customers.get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid number");
            } catch (InputMismatchException e) {
                System.out.println("Enter an integer");
            }
        }
    }

    /**
     * this method will allow an admin to select what movie they would like to remove from the database
     * this method already includes validation for stock and if the movie exists in
     * the DB due to the use of the filter.
     * 
     * @param {BookRentalSystem} - represents the whole system
     * @return - the movie selected from the system
     */
    public static Movie getMovieFromSystem(BookRentalSystem system) {
        System.out.println("The movies in the system are:");
        printMovies(system.getMovies());
        System.out.println("Please select the number of the movie you would like to select");
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return system.getMovies().get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid number");
            } catch (InputMismatchException e) {
                System.out.println("Enter a number");
            }
        }

    }

    /**
     * this method takes all necessary input to create a new movie. 
     * this will be used by the system menu in order to add a movie.
     * @return - returns the new movie to be added to the database
     */
    public static Movie createMovie()
    {
        System.out.println("Enter the title of the movie: ");
        String title = scan.nextLine();
        System.out.println("Enter the genre of the movie: ");
        String genre = scan.nextLine();
        
        System.out.println("Enter the description of the movie: ");
        String description = scan.nextLine();
        
        System.out.println("Enter the trailer URL of the movie: ");
        String url = scan.nextLine();
        while(true)
        {
            try {
                System.out.println("Enter the duration of the movie: ");
                int duration = Integer.parseInt(scan.nextLine());
                System.out.println("Enter the price of the movie: ");
                double price = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the price of the movie: ");
                int stock = Integer.parseInt(scan.nextLine());
                System.out.println("Is it a digital movie (enter 1) or a DVD (enter 2)?");
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice)
                {
                    case 1:
                        System.out.println("Enter the filesize of the movie: ");
                        int filesize = Integer.parseInt(scan.nextLine());
                        return(new DigitalMovie(title, genre,
                        duration, description, price, filesize, stock, url));
                    case 2:
                        return(new DVD(title, genre,
                        duration, description, price, stock, url));
                    default:
                        System.out.println("Input invalid! choose a 1 or 2");
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter the appropriate numeric input");
            }
        }
    }

    /**
     * this method invokes the necessary code to select a movie from the database 
     * whose trailer they would like to view
     */
    public static void playTrailer(BookRentalSystem system) {
        System.out.println("Would you like to watch a trailer for one of these movies? Enter \"y\" if yes");
        String decision = scan.nextLine();
        boolean isSuccessful = false;
        if (decision.equals("y")) {
            System.out.println("Chose your movie by the number that precedes each movie");
            while (!isSuccessful) {
                try {
                    int movieIndex = Integer.parseInt(scan.nextLine());
                    Movie chosenMovie = system.getMovies().get(movieIndex - 1);
                    chosenMovie.playTrailer();
                    isSuccessful = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please select valid movie number.");
                } catch (NumberFormatException e) {
                    System.out.println("Not a number, try again!");
                }
            }
        }
        return;
    }
}

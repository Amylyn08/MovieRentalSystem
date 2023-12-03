package moviestore;

import java.util.List;
import java.util.Scanner;

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
import moviestore.products.*;
import java.io.IOException;

/** Allows admin to use movie services and manipulate the data(s). */
public class Admin {
    public static final Scanner scan = new Scanner(System.in);
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHTYELLOW_STRING = "\033[1;93m";

    public static void main(String[] args) {
        IDatabase loader = new FileLoader();
        try {
            List<Movie> movies = loader.loadMovies();
            List<Customer> customers = loader.loadCustomers();
            MovieRentalSystem movieSystem = new MovieRentalSystem(movies, customers);
            mainMenu(movieSystem);
        } catch (LoaderFailedException e) {
            System.out.println(ANSI_RED + "movies could not be properly loaded" + ANSI_RESET);
        }
    }

    /**
     * this function represents the main menu with MAIN FUNCTIONS
     */
    public static void mainMenu(MovieRentalSystem system) {
        int input = 0;
        System.out.println(
                "\n --------------------- " + ANSI_BRIGHTYELLOW_STRING + "MAIN MENU" + ANSI_RESET
                        + " ----------------------- \n Here are your options.. choose wisely: ");
        System.out.println("1. View all movies");
        System.out.println("2. View movies sorted by a criteria");
        System.out.println("3. View movies filtered by a criteria");
        System.out.println("4. Manage system");
        System.out.println("5. EXIT \n");
        System.out.println(ANSI_YELLOW + "Enter the number of the option you would like to select: " + ANSI_RESET);
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
                    System.out.println(ANSI_GREEN + "Chosen \"EXIT\". System exiting.. Goodbye!!" + ANSI_RESET);
                    try {
                        Modifier m = new Modifier();
                        m.addNewMovies(system.getMovies());
                        m.addNewCustomers(system.getCustomers());
                    }
                    catch (IOException e)
                    {
                        System.out.println("Could not rewrite movies into csv file");
                    }
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
            }

        } while (true);

    }

    /**
     * this function displays the menu options for sorting
     */
    public static void sortedMenuOptions() {
        System.out.println("\n-------------------------" + ANSI_BRIGHTYELLOW_STRING + "SORT MENU" + ANSI_RESET
                + "----------------------------- ");
        System.out.println("1. View movies sorted by duration");
        System.out.println("2. View movies sorted by price");
        System.out.println("3. View movies sorted by rating");
        System.out.println("4. View movies sorted by title");
        System.out.println("5. BACK \n");
        System.out.println(ANSI_YELLOW + "Enter the number of the option you would like to select: " + ANSI_RESET);
    }

    /**
     * this method allows the user to interact with the sorting menu by taking their
     * input corresponding
     * to the number of the option they chose
     */
    public static void viewSortedMovies(MovieRentalSystem system) {
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
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
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
        System.out.println("\n------------------------ " + ANSI_BRIGHTYELLOW_STRING + "FILTER MENU" + ANSI_RESET
                + " -------------------------- ");
        System.out.println("1. View movies fltered by available");
        System.out.println("2. View movies filtered by genre");
        System.out.println("3. View movies filtered by title");
        System.out.println("4. BACK \n");
        System.out.println(ANSI_YELLOW + "Enter the number of the option you would like to select: " + ANSI_RESET);
    }

    /**
     * this method allows the user to interact with the sorting menu by taking their
     * input corresponding
     * to the number of the option they chose
     */
    public static void viewFilteredMovies(MovieRentalSystem system) {
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
                    System.out.println(ANSI_GREEN + "The movies that have enough stock are:" + ANSI_RESET);
                    system.setFilter(new FilterByAvailable());
                    break;
                case 2:
                    System.out.println("\033c");
                    System.out.println(ANSI_YELLOW + "Enter the genre: " + ANSI_RESET);
                    String genre = scan.nextLine();
                    system.setFilter(new FilterByGenre(genre));
                    break;
                case 3:
                    System.out.println("\033c");
                    System.out.println(ANSI_YELLOW + "Enter the title snippet: " + ANSI_RESET);
                    String titleSnip = scan.nextLine();
                    system.setFilter(new FilterByTitle(titleSnip));
                    break;
                case 4:
                    System.out.println("\033c");
                    mainMenu(system);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
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
        System.out.println("\n------------------" + ANSI_BRIGHTYELLOW_STRING + "MANAGE MENU" + ANSI_RESET
                + "------------------------ ");
        System.out.println("1. Remove a customer");
        System.out.println("2. Add a customer");
        System.out.println("3. Remove a movie");
        System.out.println("4. Add a movie");
        System.out.println("5. BACK \n");
        System.out.println(ANSI_YELLOW + "Enter the number of the option you would like to select: " + ANSI_RESET);
    }

    /**
     * this method allows the user to interact with the system managing menu by
     * taking their
     * input corresponding
     * to the number of the option they chose
     */
    public static void manageSystem(MovieRentalSystem system) {
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
                    System.out.println(ANSI_GREEN + "Customer removed successfully!" + ANSI_RESET);
                    break;
                case 2:
                    System.out.println("\033c");
                    addCustomerToSystem(system);
                    break;
                case 3:
                    System.out.println("\033c");
                    Movie movieToRemove = getMovieFromSystem(system);
                    system.setFilter(new FilterByTitle(movieToRemove.getTitle()));
                    List<Movie> filtered = system.getFilter().filterMovies(system.getMovies());
                    system.removeMovie(filtered);
                    System.out.println(ANSI_GREEN + "Movie removed successfully!" + ANSI_RESET);
                    break;
                case 4:
                    System.out.println("\033c");
                    createMovie(system);
                    System.out.println(ANSI_GREEN + "Movie added successfully!" + ANSI_RESET);
                    break;
                case 5:
                    System.out.println("\033c");
                    mainMenu(system);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                    continue;
            }
            manageSystemOptions();
        } while (true);
    }

    /**********************************************
     * HELPER METHODS
     ******************************************/

    /**
     * this method will print all movies
     * 
     * @param {List<Movie>} - represents the movies to print
     */
    public static void printMovies(List<Movie> movies) {
        int index = 1;
        for (Movie m : movies) {
            System.out.println(index + ". " + m);
            index++;
        }
        if (movies.size() == 0)
            System.out.println(ANSI_RED + "No movies available!" + ANSI_RESET);
    }

    /**
     * this method will print all customers
     * 
     * @param {List<Customer>} - represents the list of customers to print
     */
    public static void printCustomers(List<Customer> cus) {
        int index = 1;
        for (Customer c : cus) {
            System.out.println(index + ". " + c);
            index++;
        }
        if (cus.size() == 0)
            System.out.println(ANSI_RED + "No customers available!" + ANSI_RESET);
    }

    /**
     * this method allows the admin to create a new customer
     * and add that customer to the system.
     * 
     * @param {BookRentalSystem} - represents the system
     */
    public static void addCustomerToSystem(MovieRentalSystem system) {
        System.out.println(ANSI_YELLOW + "Please enter the name of the customer:" + ANSI_RESET);
        Customer cusToAdd = new Customer(scan.nextLine());
        try {
            system.addCustomer(cusToAdd);
            System.out.println(ANSI_GREEN + "Customer added successfully!" + ANSI_RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
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
        System.out.println(ANSI_GREEN + "Select the customer you would like to manage" + ANSI_RESET);
        printCustomers(customers);
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return customers.get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Please select a valid number" + ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Enter an integer" + ANSI_RESET);
            }
        }
    }

    /**
     * this method will allow an admin to select what movie they would like to
     * remove from the database
     * this method already includes validation for stock and if the movie exists in
     * the DB due to the use of the filter.
     * 
     * @param {BookRentalSystem} - represents the whole system
     * @return - the movie selected from the system
     */
    public static Movie getMovieFromSystem(MovieRentalSystem system) {
        System.out.println(ANSI_GREEN + "The movies in the system are:" + ANSI_RESET);
        printMovies(system.getMovies());
        System.out.println(ANSI_YELLOW + "Please select the number of the movie you would like to select" + ANSI_RESET);
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return system.getMovies().get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Please select a valid number" + ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Enter a number" + ANSI_RESET);
            }
        }

    }

    /**
     * this method takes all necessary input to create a new movie.
     * this will be used by the system menu in order to add a movie.
     * 
     * @return - returns the new movie to be added to the database
     */
    public static void createMovie(MovieRentalSystem system) {
        System.out.println(ANSI_YELLOW + "Enter the title of the movie: " + ANSI_RESET);
        String title = scan.nextLine();
        System.out.println(ANSI_YELLOW + "Enter the genre of the movie: " + ANSI_RESET);
        String genre = scan.nextLine();

        System.out.println(ANSI_YELLOW + "Enter the description of the movie: " + ANSI_RESET);
        String description = scan.nextLine();

        System.out.println(ANSI_YELLOW + "Enter the trailer URL of the movie: " + ANSI_RESET);
        String url = scan.nextLine();
        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Enter the duration of the movie: " + ANSI_RESET);
                int duration = Integer.parseInt(scan.nextLine());
                System.out.println(ANSI_YELLOW + "Enter the price of the movie: " + ANSI_RESET);
                double price = Double.parseDouble(scan.nextLine());
                System.out.println(ANSI_YELLOW + "Enter the stock of the digital movie: " + ANSI_RESET);
                int stock = Integer.parseInt(scan.nextLine());
                System.out.println(ANSI_YELLOW + "Enter the stock of the DVD movie: " + ANSI_RESET);
                int stockDVD = Integer.parseInt(scan.nextLine());
                System.out.println(ANSI_YELLOW + "Enter the filesize of the movie: " + ANSI_RESET);
                int filesize = Integer.parseInt(scan.nextLine());
                system.addMovie(new DigitalMovie(title, genre,
                                duration, description, price, filesize, stock, url));
                system.addMovie(new DVD(title, genre,
                                duration, description, price, stockDVD, url));
                break;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter the appropriate numeric input" + ANSI_RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            }
        }
    }

    /**
     * this method invokes the necessary code to select a movie from the database
     * whose trailer they would like to view
     * 
     */
    public static void playTrailer(MovieRentalSystem system) {
        System.out.println(ANSI_YELLOW + "Would you like to watch a trailer for one of these movies? Enter \"y\" if yes"
                + ANSI_RESET);
        String decision = scan.nextLine();
        boolean isSuccessful = false;
        if (decision.equals("y")) {
            System.out.println(ANSI_YELLOW + "Chose your movie by the number that precedes each movie" + ANSI_RESET);
            while (!isSuccessful) {
                try {
                    int movieIndex = Integer.parseInt(scan.nextLine());
                    Movie chosenMovie = system.getMovies().get(movieIndex - 1);
                    chosenMovie.playTrailer();
                    isSuccessful = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ANSI_RED + "Please select valid movie number." + ANSI_RESET);
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_RED + "Not a number, try again!" + ANSI_RESET);
                }
            }
        }
        return;
    }
}

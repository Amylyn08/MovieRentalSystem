package moviestore;

import java.util.*;
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

/**
 * Functinality is to allow employees to use the movie services.
 */
public class Employee {
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
        System.out.println("4. Manage customers");
        System.out.println("5. Add a review for a movie");
        System.out.println("6. EXIT \n");
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
                    manageCustomers(system);
                    break;
                case 5:
                    printMovies(system.getMovies());
                    addRatingtoMovie(system);
                    mainMenu(system);
                    break;
                case 6:
                    System.out.println(ANSI_GREEN + "Chosen \"EXIT\". System exiting.. Goodbye!!" + ANSI_RESET);
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
     * this function displays the menu options for filtering
     */
    public static void customerMenuOptions() {
        System.out.println("\n------------------------- " + ANSI_BRIGHTYELLOW_STRING + "CUSTOMER MENU" + ANSI_RESET
                + " ------------------------ ");
        System.out.println("1. Rent movie");
        System.out.println("2. Return movie");
        System.out.println("3. Select a new customer");
        System.out.println("4. BACK \n");
        System.out.println(ANSI_YELLOW + "Enter the number of the option you would like to select: " + ANSI_RESET);
    }

    /**
     * this method allows the user to interact with the customer menu by taking
     * their input corresponding
     * to the number of the option they chose
     */
    public static void manageCustomers(MovieRentalSystem system) {
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
                    System.out.println(ANSI_YELLOW + "Would you like to use your points? y/n" + ANSI_RESET);
                    String ans = "";
                    do {
                        ans = scan.nextLine();
                    } while (!ans.equals("y") && !ans.equals("n"));
                    double price = toRent.getPrice();
                    if (ans.equals("y")) {
                        System.out.println(ANSI_GREEN + "Price after discount is: " + payRent(toRent, selected) + "$"
                                + ANSI_RESET);
                    }
                    System.out.println("Your total is: " + price);
                    system.rentMovie(toRent);
                    selected.rentMovie(toRent);
                    System.out.println(ANSI_GREEN + "Movie rented successfully" + ANSI_RESET);
                    break;
                case 2:
                    System.out.println("\033c");
                    if (selected.getRentedMovies().size() == 0)
                        System.out.println(ANSI_GREEN + "Nothing to return!" + ANSI_RESET);
                    else {
                        Movie toReturn = movieToReturn(selected);
                        system.returnMovie(toReturn);
                        selected.returnMovie(toReturn);
                        System.out.println(ANSI_GREEN + "Movie returned successfully" + ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                    continue;
            }
            customerMenuOptions();
        } while (true);
    }

    /**********************************************
     * HELPER METHODS
     ******************************************/
    /**
     * Prints all the movies.
     * 
     * @param movies list of movies
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
     * Prints all the customer objects in list.
     * 
     * @param cus List of customers
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
     * this method allows the employee to select which customer
     * they would like to manage
     * 
     * @param {MovieRentalSystem} - represents the whole system
     * @return - the movie to be returned
     */
    public static Customer getCustomer(List<Customer> customers) {
        System.out.println(ANSI_YELLOW + "Select the customer you would like to manage" + ANSI_RESET);
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
     * this method will allow an employee to select a movie the customer can rent by
     * allowing
     * them to select from ONLY AVAILABLE MOVIES .
     * this method already includes validation for stock and if the movie exists in
     * the DB
     * due to the use of the filter.
     * 
     * @param {MovieRentalSystem} - represents the whole system
     * @return - the movie to be returned
     */
    public static Movie getMovie(MovieRentalSystem system) {
        System.out.println(ANSI_GREEN + "The movies that have enough stock are:" + ANSI_RESET);
        system.setFilter(new FilterByAvailable());
        List<Movie> filtered = system.getFilter().filterMovies(system.getMovies());
        printMovies(filtered);
        System.out.println(ANSI_YELLOW + "Please select the number of the movie you would like to select" + ANSI_RESET);
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return filtered.get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Please select a valid number" + ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Enter a number" + ANSI_RESET);
            }
        }

    }

    /**
     * this method asks the customer which points discount they would like to use
     * (if any)
     * and returns the final price after the discount has been applied
     * 
     * @param {Movie}    - represents the movie being managed
     * @param {Customer} - represents the customer being managed
     * @return - the final price after discount
     */
    public static double payRent(Movie m, Customer c) {
        int MINPOINTS = 10000;
        IDiscountStrategy[] discounts = { new FiveDollarDiscount(), new TenDollarDiscount(), new TwentyDollarDiscount(),
                new FiftyDollarDiscount() };
        if (c.getPoints() < MINPOINTS) {
            System.out.println("You dont have enough points!");
            return m.getPrice();
        }
        while (true) {
            try {
                System.out.println("Enter the discount you would like to use: \n 1) 5$ \n 2) 10$ \n 3) 20$ \n 4) 50$");
                int input = Integer.parseInt(scan.nextLine());
                return discounts[input - 1].finalPrice(c, m);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid number");
            } catch (NumberFormatException e) {
                System.out.println("Enter an integer");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * this method allows the employee to select which of the customer's movies will
     * be returned.
     * 
     * @param {Customer} - represents the customer being managed
     * @return - the movie to be returned
     */
    public static Movie movieToReturn(Customer c) {
        List<Movie> cusMovies = c.getRentedMovies();
        System.out.println(ANSI_YELLOW + "Select the movie you would like to return" + ANSI_RESET);
        printMovies(cusMovies);
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                return cusMovies.get(input - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Please select a valid number" + ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Enter an integer" + ANSI_RESET);
            }
        }
    }

    /**
     * This function plays a trailer for any movie that the user wishes.
     * 
     * @param system - the manipulable MovieRentalSystem object.
     */
    public static void playTrailer(MovieRentalSystem system) {
        System.out.println(ANSI_YELLOW
                + "Would you like to watch a trailer for one of these movies? Enter \"y\" if yes, anything else if no"
                + ANSI_RESET);
        String decision = scan.nextLine();
        boolean isSuccessful = false;
        if (decision.equals("y")) {
            System.out.println(ANSI_YELLOW + "Chose your movie by the number that precedes each movie" + ANSI_RESET);
            while (!isSuccessful) {
                try {
                    int movieIndex = Integer.parseInt(scan.nextLine());
                    Movie chosenMovie = system.getMovies().get(movieIndex - 1);
                    System.out.println(ANSI_GREEN + "Trailer opened in browser!..." + ANSI_RESET);
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

    /**
     * This function that adds a rating to a movie, taking in user input to pick a
     * movie and a desired rating.
     * 
     * @param system - The manipulatable MovieRentalSystem object
     */
    public static void addRatingtoMovie(MovieRentalSystem system) {
        while (true) {
            try {
                System.out.println(ANSI_YELLOW + "Enter number of movie to rate" + ANSI_RESET);
                int index = Integer.parseInt(scan.nextLine()) - 1;
                System.out.println(ANSI_YELLOW + "Enter the rating" + ANSI_YELLOW);
                double rating = Double.parseDouble(scan.nextLine());
                system.getMovies().get(index).addRating(rating);
                System.out.println(ANSI_GREEN + "\n Added rating succesfully!" + ANSI_RESET);
                return;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a NUMBER." + ANSI_RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Invalid movie number :( try again!" + ANSI_RESET);
            }
        }
    }
}

package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public interface IDiscountStrategy {
    double finalPrice(Customer c, Movie m);
}

/*
 * 5$ => 10000 points deducted
 * 10$ 15000 points deducted
 * 20$ 25000 points deducted
 * 50$ 100000 points deducted
 */

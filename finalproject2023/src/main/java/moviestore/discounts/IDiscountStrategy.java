package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public interface IDiscountStrategy {
    double finalPrice(Customer c, Movie m);
}

/*
 * 5$ => 10000
 * 10$ 15000
 * 20$ 25000
 * 50$ 100000
 */

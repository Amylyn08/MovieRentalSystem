package moviestore;

import moviestore.products.Movie;

public interface IDiscountStrategy {
    double finalPrice(Customer c, Movie m);
}

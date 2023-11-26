package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class TwentyDollarDiscount {
    public double finalPrice(Customer c, Movie m) {
        if (c.getPoints() < 25000) {
            throw new IllegalArgumentException("Customer does not have enough points!");
        }
        c.deductPoints(25000);
        return m.getPrice() - 20;
    }
}

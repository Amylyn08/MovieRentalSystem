package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class TenDollarDiscount {
    public double finalPrice(Customer c, Movie m) {
        if (c.getPoints() < 15000) {
            throw new IllegalArgumentException("Customer does not have enough points!");
        }
        c.deductPoints(15000);
        return m.getPrice() - 10;
    }
}

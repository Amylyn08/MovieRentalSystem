package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class FiveDollarDiscount implements IDiscountStrategy {
    public double finalPrice(Customer c, Movie m) {
        if (c.getPoints() < 10000) {
            throw new IllegalArgumentException("Customer does not have enough points :()");
        }
        c.deductPoints(10000);
        return m.getPrice() - 5;
    }
}

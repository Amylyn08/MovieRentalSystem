package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class FiftyDollarDiscount implements IDiscountStrategy {
    public double finalPrice(Customer c, Movie m) {
        if (c.getPoints() < 100000) {
            throw new IllegalArgumentException("Customer does not have enough points :()");
        }
        c.deductPoints(100000);
        return m.getPrice() - 50;
    }

}

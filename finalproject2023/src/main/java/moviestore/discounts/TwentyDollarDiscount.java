package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class TwentyDollarDiscount implements IDiscountStrategy {
    public double finalPrice(Customer c, Movie m) {
        c.deductPoints(25000);
        return m.getPrice() - 20;
    }
}

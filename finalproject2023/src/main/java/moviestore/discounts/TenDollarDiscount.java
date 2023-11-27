package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class TenDollarDiscount implements IDiscountStrategy {
    public double finalPrice(Customer c, Movie m) {
        c.deductPoints(15000);
        return m.getPrice() - 10;
    }
}

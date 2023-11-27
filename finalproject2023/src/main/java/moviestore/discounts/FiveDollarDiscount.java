package moviestore.discounts;

import moviestore.Customer;
import moviestore.products.Movie;

public class FiveDollarDiscount implements IDiscountStrategy {

    /**
     * Returns the final price after points have been deducted form customer
     * 
     * @param {c} - the customer that the discount is being applied to.
     * @param {m} - The movie object that the discount is being applied to.
     * @return - The price of the movie after the discount has been applied.
     * @throws IllegalArgumentException - If the user does not have sufficient funds
     *                                  (less than 10000 points).
     */
    public double finalPrice(Customer c, Movie m) {
        if (c.getPoints() < 10000) {
            throw new IllegalArgumentException("Customer does not have enough points :()");
        }
        c.deductPoints(10000);
        return m.getPrice() - 5;
    }
}

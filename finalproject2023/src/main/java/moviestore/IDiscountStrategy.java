package moviestore;

public interface IDiscountStrategy {
    double finalPrice(Customer c, Movie m);
}

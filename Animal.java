import java.util.Random;

public abstract class Animal {
    private static Random rg = new Random();

    private double price;

    protected Animal(double minPrice, double maxPrice) {
        double price = minPrice + rg.nextDouble() * (maxPrice - minPrice);
        setPrice(price);
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%.02f EUR)", getClass().getSimpleName(), getPrice());
    }
}

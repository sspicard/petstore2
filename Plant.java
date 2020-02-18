public abstract class Plant {
    private double price;

    protected Plant(double price) {
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

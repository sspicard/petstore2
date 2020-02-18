import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Basket {
    private Set<Animal> contents;

    public Basket() {
        contents = new HashSet<Animal>();
    }

    public void add(Animal animal) {
        contents.add(animal);
    }

    public void remove(Animal animal) {
        contents.remove(animal);
    }

    public Collection<Animal> getContents() {
        return contents;
    }

    public double getTotalPrice() {
        double price = 0.0;
        for (Animal animal : contents) {
            price += animal.getPrice();
        }
        return price;
    }
}

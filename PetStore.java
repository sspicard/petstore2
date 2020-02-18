import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PetStore {
    private List<Animal> inventory;

    public PetStore(int count, List<Class<? extends Animal>> speciesList) {
        inventory = new ArrayList<Animal>();
        fillInventory(count, speciesList);
    }

    private void fillInventory(int count, List<Class<? extends Animal>> speciesList) {
        Random rg = new Random();
        Class<? extends Animal> species;
        Animal animal;
        Constructor<? extends Animal> constr;
        for (int i = 0; i < count; ++i) {
            species = speciesList.get(rg.nextInt(speciesList.size()));
            try {
                constr = species.getConstructor();
                animal = constr.newInstance();
                inventory.add(animal);
            } catch (Exception e) {
                System.err.printf("Fout bij aanmaken %s: %s%n", species.getSimpleName(), e.getMessage());
            }
        }
    }

    public void run() {
        Basket basket = new Basket();
        Scanner in = new Scanner(System.in);
        Animal purchase;
        System.out.println("We hebben volgende dieren in de aanbieding:\n");
        purchase = getPurchase(in);
        while (purchase != null) {
            basket.add(purchase);
            inventory.remove(purchase);
            System.out.printf("%nJe hebt %s gekocht. Wil je nog een dier kopen?%n%n", purchase);
            purchase = getPurchase(in);
        }
        System.out.printf("%nDe totale waarde van je aankopen bedraagt %.02f EUR.%n", basket.getTotalPrice());
    }

    private Animal getPurchase(Scanner in) {
        printInventory();
        return requestPurchase(in);
    }

    private Animal requestPurchase(Scanner in) {
        String input;
        int choice;
        System.out.println("Voer het nummer in van het dier dat je wenst aan te kopen.");
        System.out.println("Om te stoppen, voer 0 in.");
        input = in.nextLine();
        while (!input.matches("[0-9]+") || (choice = Integer.parseInt(input)) > inventory.size()) {
            System.out.println("Ongeldige keuze. Probeer opnieuw.");
            input = in.nextLine();
        }
        return choice == 0 ? null : inventory.get(choice - 1);
    }

    private void printInventory() {
        int i = 0;
        for (Animal animal : inventory) {
            System.out.printf("%4d. %s%n", ++i, animal);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Class<? extends Animal>> species = new ArrayList<Class<? extends Animal>>();
        species.add(Pangolin.class);
        species.add(Lemur.class);
        species.add(Tapir.class);
        species.add(Unicorn.class);
        PetStore store = new PetStore(10, species);
        store.run();
    }
}

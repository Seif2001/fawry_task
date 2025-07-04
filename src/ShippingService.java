import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

public class ShippingService {
    public void shipItems(ArrayList<SimpleEntry<Shippable, Integer>> items) {
        System.out.println("Shipping the following items:");
        if (items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        double totalWeight = 0.0;
        for (SimpleEntry<Shippable, Integer> entry : items) {
            Shippable item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item: " + item.getName() + ", Quantity: " + quantity + ", Weight: " + item.getWeight() * quantity);
            totalWeight += item.getWeight() * quantity;
        }
        System.out.println("Total weight of shipment: " + totalWeight + " kg");
    }
}

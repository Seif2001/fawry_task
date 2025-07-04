import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
public class Cart {
    private ArrayList<SimpleEntry<Product,Integer>> products;
    private int productCount;

    public Cart() {
        this.products = new ArrayList<>();
        this.productCount = 0;
        
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if(product.getExpiresIn() == 0) {
            throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
        }
        for (int i = 0; i < products.size(); i++) {
            SimpleEntry<Product, Integer> entry = products.get(i);
            if (entry.getKey().getName().equals(product.getName())) {
                int newQuantity = entry.getValue() + quantity;
                products.set(i, new SimpleEntry<>(product, newQuantity));
                product.setQuantity(product.getQuantity() - quantity);
                productCount += quantity;
                return;
            }
        }        
        if(quantity <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - quantity);
            products.add(new SimpleEntry<>(product, quantity));
            productCount+= quantity;
        } else {
            throw new IllegalArgumentException("Not enough quantity available for " + product.getName());
        }
    }

    public void remove(String productName, int quantityToRemove) {
        for(int i = 0; i < products.size(); i++){
            SimpleEntry<Product, Integer> entry = products.get(i);
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if(product.getName().equals(productName)) {
                if(quantityToRemove <= quantity) {
                    product.setQuantity(product.getQuantity() + quantityToRemove);
                    products.set(i, new SimpleEntry<>(product, quantity - quantityToRemove));
                    productCount -= quantityToRemove;
                    if(products.get(i).getValue() == 0) {
                        products.remove(i);
                    }
                } else {
                    throw new IllegalArgumentException("Not enough quantity in cart to remove " + quantityToRemove + " of " + productName);
                }
                return;
            }
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getKey().getPrice() * products.get(i).getValue();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n-------------------------------------- Cart contains -------------------------------------------\n\n");

        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i).getKey().toString()).append(" x ").append(products.get(i).getValue()).append("\n");
        }
        sb.append("\n------------------------------------- Checkout Receipt ----------------------------------------\n\n");
        sb.append(getCheckoutReciept());
        return sb.toString();
    }
    public void clear(boolean restoreQuantity) {
        if (restoreQuantity) {
            for (SimpleEntry<Product, Integer> entry : products) {
                Product product = entry.getKey();
                product.setQuantity(product.getQuantity() + entry.getValue());
            }
        }
        products.clear();
        productCount = 0;
    }
    public void getShippingDetails() {
        ArrayList<SimpleEntry<Shippable, Integer>> shippableProducts = new ArrayList<>();
        System.out.println("Shipping Details:");
        for (SimpleEntry<Product, Integer> entry : products) {
            Product product = entry.getKey();
            if (product.isShippable()) {
                shippableProducts.add(new SimpleEntry<>(product, entry.getValue()));
                
            } 
              
        }
        ShippingService shippingService = new ShippingService();
        shippingService.shipItems(shippableProducts);
    }

    public String getCheckoutReciept() {
        StringBuilder sb = new StringBuilder("Checkout Receipt:\n");
        double total = calculateTotal();
        sb.append("Products purchased:\n");
        for (SimpleEntry<Product, Integer> entry : products) {
            sb.append(entry.getKey().getName()).append(" x ").append(entry.getValue());
            double price = entry.getKey().getPrice() * entry.getValue();
            sb.append(" Price: $").append(price).append("\n");
        }
        sb.append("Total amount: $").append(total).append("\n");
        return sb.toString();
    }
}

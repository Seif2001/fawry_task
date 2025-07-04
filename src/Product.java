public class Product implements Shippable {
    private String name;
    private double price;
    private int quantity;
    private boolean expires;
    private boolean isShippable;
    private double weight = 0.0;
    private int expiresIn = -1; // days, -1 means no expiration

    public Product(String name, double price, int quantity, boolean expires, boolean isShippable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expires = expires;
        this.isShippable = isShippable;
        if(expires) {
            this.expiresIn = 1; // Default until set explicitly
        }
    }
    public Product(String name, double price, int quantity) {
        this(name, price, quantity, false, true);
    }
    public Product(String name, double price) {
        this(name, price, 0, false, true);
    }

    public void setWeight(double weight) {
        if(isShippable){
            this.weight = weight;
        }
        else {
            throw new IllegalArgumentException("Weight can only be set for shippable products.");
        }
    }

    public void setExpiresIn(int expiresIn) {
        if (expires) {
            this.expiresIn = expiresIn;
        } else {
            throw new IllegalArgumentException("Cannot set expiration for non-expiring products.");
        }
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public boolean isShippable() {
        return isShippable;
    }

    public double getWeight() {
        return weight;
    }


    public int getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + 
                ", quantity=" + quantity + ", expires=" + expires +
                ", isShippable=" + isShippable + ", weight=" + weight + '}';
    }
}

public class Customer {
    private String name;
    private double balance = 0.0;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.cart = new Cart();
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public Cart getCart() {
        return cart;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void reduceBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance to reduce by " + amount);
        }
    }
    public void addToCart(Product product, int quantity) {
        try {
            cart.add(product, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeFromCart(String productName, int quantity) {
        try {
            cart.remove(productName, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void viewCart() {
        System.out.println(cart.toString());
        System.out.println("-------------------------------------- Shipping Details ----------------------------------------\n");
        cart.getShippingDetails();
        System.out.println("-------------------------------------- End of Shipping Details ----------------------------------------\n");
    }
    public void addBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Amount to add must be positive.");
        }
    }
    public void checkout() {
        double total = cart.calculateTotal();
        if (total <= balance) {
            reduceBalance(total);
            System.out.println("Checkout successful. Total: " + total);
            System.out.println("Remaining balance: " + balance);
            cart.clear(false);
        } else {
            System.out.println("Insufficient balance for checkout. Total: " + total + ", Balance: " + balance);
        }
    }
}

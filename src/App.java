public class App {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer("John Doe", 3000.0);
        Product laptop = new Product("Laptop", 999.99, 10, false, true);
        laptop.setWeight(2.5); 
        Product smartPhone = new Product("Smartphone", 499.99, 5, false, true);
        smartPhone.setWeight(0.2);

        Product Book = new Product("Book", 19.99, 20, false, false);
        Product cheese = new Product("Cheese", 5.99, 50, true, false);
        cheese.setExpiresIn(0);
        
        System.out.println("Customer: " + customer.getName() + ", Balance: $" + customer.getBalance());
        System.out.println("\n------------------------------------ Test #4: adding expired product to cart  ------------------------------------ \n");
        customer.addToCart(smartPhone, 2);
        customer.addToCart(laptop, 2);
        customer.addToCart(cheese, 2);
        customer.viewCart();
        customer.checkout();
        

    }
}

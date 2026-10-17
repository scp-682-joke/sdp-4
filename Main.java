import facade.OrderFacade;
import model.Cart;
import model.Customer;
import model.Item;

public class Main {
    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade();
        Customer customer = new Customer("C1", "Alice", "alice@example.com", "Astana");
        Cart cart = new Cart();
        cart.addItem(new Item("A", "Headphones", 9999, 1));
        cart.addItem(new Item("B", "Cable", 1500, 2));
        System.out.println("--- Successful order ---");
        String id = facade.placeOrder(cart, customer, "CARD");
        System.out.println("--- Declined payment and rollback ---");
        Cart declined = new Cart();
        declined.addItem(new Item("A", "Headphones", 9999, 1));
        facade.placeOrder(declined, customer, "INVALID");
        System.out.println("--- Cancellation ---");
        System.out.println("Cancelled: " + facade.cancelOrder(id));
        System.out.println("--- Duplicate item stock check ---");
        Cart tooMany = new Cart();
        tooMany.addItem(new Item("B", "Cable", 1500, 2));
        tooMany.addItem(new Item("B", "Cable", 1500, 2));
        facade.placeOrder(tooMany, customer, "CARD");
    }
}

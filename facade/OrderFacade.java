package facade;

import model.Cart;
import model.Customer;
import model.Item;
import subsystem.InventoryService;
import subsystem.NotificationService;
import subsystem.PaymentProcessor;
import subsystem.ShippingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class OrderFacade {
    private record Order(Customer customer, List<Item> items) { }
    private final InventoryService inventory = new InventoryService();
    private final PaymentProcessor payments = new PaymentProcessor();
    private final ShippingService shipping = new ShippingService();
    private final NotificationService notifications = new NotificationService();
    private final Map<String, Order> orders = new HashMap<>();

    public String placeOrder(Cart cart, Customer customer, String paymentMethod) {
        List<Item> items = cart.getItems();
        if (!inventory.reserveStock(items)) {
            notifications.notifyCustomer(customer.getEmail(), "Out of stock or empty cart");
            return null;
        }
        String orderId = UUID.randomUUID().toString();
        if (!payments.chargePayment(orderId, cart.getTotalAmount(), paymentMethod)) {
            inventory.releaseStock(items);
            notifications.notifyCustomer(customer.getEmail(), "Payment declined");
            return null;
        }
        try {
            shipping.scheduleShipment(orderId, customer.getShippingAddress());
        } catch (RuntimeException ex) {
            payments.refundPayment(orderId);
            inventory.releaseStock(items);
            notifications.notifyCustomer(customer.getEmail(), "Shipping failed: " + ex.getMessage());
            return null;
        }
        orders.put(orderId, new Order(customer, items));
        notifications.notifyCustomer(customer.getEmail(), "Order placed: " + orderId);
        return orderId;
    }

    public boolean cancelOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) return false;
        shipping.cancelShipment(orderId);
        payments.refundPayment(orderId);
        inventory.releaseStock(order.items());
        orders.remove(orderId);
        notifications.notifyCustomer(order.customer().getEmail(), "Order cancelled: " + orderId);
        return true;
    }
}

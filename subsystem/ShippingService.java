package subsystem;

import java.util.HashSet;
import java.util.Set;
public class ShippingService {
    private final Set<String> shipments = new HashSet<>();
    public void scheduleShipment(String orderId, String address) {
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Missing shipping address");
        shipments.add(orderId);
        System.out.println("Shipping: scheduled to " + address);
    }
    public void cancelShipment(String orderId) {
        if (!shipments.remove(orderId)) throw new IllegalStateException("Shipment not found");
        System.out.println("Shipping: cancelled");
    }
}
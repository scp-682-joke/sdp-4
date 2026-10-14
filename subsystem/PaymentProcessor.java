package subsystem;

import java.util.HashMap;
import java.util.Map;
public class PaymentProcessor {
    private final Map<String, Double> payments = new HashMap<>();
    public boolean chargePayment(String orderId, double amountCents, String paymentMethod) {
        if (!"CARD".equals(paymentMethod) || amountCents <= 0) {
            System.out.println("Payment: declined");
            return false;
        }
        payments.put(orderId, amountCents);
        System.out.println("Payment: charged " + amountCents + " cents");
        return true;
    }
    public void refundPayment(String orderId) {
        Double amount = payments.remove(orderId);
        if (amount == null) throw new IllegalStateException("Payment not found");
        System.out.println("Payment: refunded " + amount + " cents");
    }
}
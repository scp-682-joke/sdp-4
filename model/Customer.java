package model;
public class Customer {
    private final String customerId;
    private final String name;
    private final String email;
    private final String shippingAddress;

    public Customer(String customerId, String name, String email, String shippingAddress) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getShippingAddress() { return shippingAddress; }
}
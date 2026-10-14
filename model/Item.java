package model;

public class Item {
    private final String id;
    private final String name;
    private final double price;
    private final int quantity;

    public Item(String id, String name, double price, int quantity) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid item price");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return price * quantity; }
}
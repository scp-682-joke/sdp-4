package model;

public class Item {
    private final String id;
    private final String name;
    private final double price;
    private final int quantity;

    public Item(String id, String name, double price, int quantity) {
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
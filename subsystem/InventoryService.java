package subsystem;

import model.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>(Map.of("A", 5, "B", 3));
    public boolean reserveStock(List<Item> items) {
        Map<String, Integer> requested = new HashMap<>();
        for (Item item : items) requested.merge(item.getId(), item.getQuantity(), Integer::sum);
        if (requested.isEmpty()) return false;
        for (var entry : requested.entrySet())
            if (stock.getOrDefault(entry.getKey(), 0) < entry.getValue()) return false;
        requested.forEach((id, quantity) -> stock.put(id, stock.get(id) - quantity));
        System.out.println("Inventory: reserved items");
        return true;
    }
    public void releaseStock(List<Item> items) {
        for (Item item : items) stock.merge(item.getId(), item.getQuantity(), Integer::sum);
        System.out.println("Inventory: released items");
    }
    public int available(String id) { return stock.getOrDefault(id, 0); }
}

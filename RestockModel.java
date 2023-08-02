import java.util.List;
import java.util.ArrayList;

public class RestockModel {
    private Item[][] items;
    private final List<InventoryObserver> observers = new ArrayList<>();

    public RestockModel() {
        items = new Item[3][3];
        
         // Initializing the items in the vending machine
         items[0][0] = new Item("GRANOLA OATS (100g)", 10, 115, 490);
         items[0][1] = new Item("DRIED FRUIT (100g)", 10, 165, 480);
         items[0][2] = new Item("MIXED NUTS (100g)", 10, 195, 607);
         items[1][0] = new Item("MIXED SEEDS (100g)", 10, 223, 556);
         items[1][1] = new Item("PUFFED RICE (100g)", 10, 99, 402);
         items[1][2] = new Item("CHOCOLATE CHIPS (100g)", 10, 144, 479);
         items[2][0] = new Item("FREEZE DRIED BERRIES (100g)", 10, 164, 400);
         items[2][1] = new Item("SHREDDED COCONUT (100g)", 10, 126, 676);
         items[2][2] = new Item("CANDIED NUTS (100g)", 10, 180, 522); 
    
        }

    public Item getItem(int row, int col) {
        return items[row][col];
    }

    public void setItem(int row, int col, Item item) {
        items[row][col] = item;
        notifyObservers();
    }

    public void decreaseItemQuantity(int row, int col) {
        Item item = items[row][col];
        if (item != null) {
            item.decreaseQuantity();
            notifyObservers();
        }
    }

    public void restockItem(int row, int col, int quantity) {
        Item item = items[row][col];
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            notifyObservers();
        }
    }

    public void deleteItem(int row, int col) {
        items[row][col] = null;
        notifyObservers();
    }

    public void changeItemCost(int row, int col, double cost) {
        Item item = items[row][col];
        if (item != null) {
            item.setCost(cost);
            notifyObservers();
        }
    }

    public Item[][] getItems() {
        return items;
    }

    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (InventoryObserver observer : observers) {
            observer.inventoryChanged();
        }
    }
}

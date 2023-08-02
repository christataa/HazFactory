import java.util.List;
import java.util.ArrayList;

public class SpecialRestockModel {
    private SpecialItem[][] items;
    private int[][] quantities;
    private final List<SpecialInventoryObserver> observers = new ArrayList<>();

    public SpecialRestockModel() {
        items = new SpecialItem[3][3];
        quantities = new int[3][3];

        // Initializing the items in the vending machine
        items[0][0] = new SpecialItem("GRANOLA OATS (100g)", 115, 490);
        items[0][1] = new SpecialItem("DRIED FRUIT (100g)", 165, 480);
        items[0][2] = new SpecialItem("MIXED NUTS (100g)", 195, 607);
        items[1][0] = new SpecialItem("MIXED SEEDS (100g)", 223, 556);
        items[1][1] = new SpecialItem("PUFFED RICE (100g)", 99, 402);
        items[1][2] = new SpecialItem("CHOCOLATE CHIPS (100g)", 144, 479);
        items[2][0] = new SpecialItem("FREEZE DRIED BERRIES (100g)", 164, 400);
        items[2][1] = new SpecialItem("SHREDDED COCONUT (100g)", 126, 676);
        items[2][2] = new SpecialItem("CANDIED NUTS (100g)", 180, 522);

        // Initializing quantities for each item in the vending machine
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                quantities[i][j] = 10;
            }
        }
    }

    public SpecialItem getItem(int row, int col) {
        return items[row][col];
    }

    public void setItem(int row, int col, SpecialItem item, int quantity) {
        items[row][col] = item;
        quantities[row][col] = quantity;
        notifyObservers();
    }

    public void decreaseItemQuantity(int row, int col) {
        if (quantities[row][col] > 0) {
            quantities[row][col]--;
            notifyObservers();
        }
    }

    public void restockItem(int row, int col, int quantity) {
        quantities[row][col] += quantity;
        notifyObservers();
    }

    public void deleteItem(int row, int col) {
        items[row][col] = null;
        quantities[row][col] = 0;
        notifyObservers();
    }

    public void changeItemCost(int row, int col, double cost) {
        SpecialItem item = items[row][col];
        if (item != null) {
            item.setCost(cost);
            notifyObservers();
        }
    }

    public SpecialItem[][] getItems() {
        return items;
    }

    public int getQuantity(int row, int col) {
        return quantities[row][col];
    }

    public void setQuantity(int row, int col, int quantity) {
        quantities[row][col] = quantity;
        notifyObservers();
    }

    public void addObserver(SpecialInventoryObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (SpecialInventoryObserver observer : observers) {
            observer.inventoryChanged();
        }
    }
}

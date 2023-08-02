import java.util.*;

public class GranolaModel {
    private GranolaItem[][] itemList;
    private List<GranolaInventoryObserver> observers;

    private static final String[] NAMES = {"Rolled Oats", "Nuts", "Seeds", "Dried Fruit", "Puffed Rice", "Chocolate Chips", "Freeze-dried Berries", "Shredded Coconut", "Candied Almonds"};
    private static final double[] PRICES = {23, 39, 44.6, 33, 19.8, 48.8, 32.8, 10, 10};
    private static final int[] CALORIES = {132, 172, 158, 102, 117, 136, 96, 169, 112};
    private static final int[] QUANTITIES = {10, 10, 10, 10, 10, 10, 10, 10, 10};

    public GranolaModel() {
        this.itemList = new GranolaItem[3][3];
        this.observers = new ArrayList<>();
        initializeItems();
    }

    public void addObserver(GranolaInventoryObserver observer) {
        this.observers.add(observer);
    }

    public GranolaItem getItem(int row, int col) {
        return this.itemList[row][col];
    }

    public void setItem(int row, int col, GranolaItem item) {
        this.itemList[row][col] = item;
        notifyObservers();
    }

    public void deleteItem(int row, int col) {
        this.itemList[row][col] = null;
        notifyObservers();
    }

    public void changeItemCost(int row, int col, double cost) {
        GranolaItem item = this.itemList[row][col];
        if (item != null) {
            item.setCost(cost);
            notifyObservers();
        }
    }

    public void restockItem(int row, int col, int quantity) {
        GranolaItem item = this.itemList[row][col];
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            notifyObservers();
        }
    }

    private void notifyObservers() {
        for (GranolaInventoryObserver observer : observers) {
            observer.granolaInventoryChanged();
        }
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        for (GranolaItem[] row : itemList) {
            for (GranolaItem item : row) {
                if (item != null) {
                    totalCost += item.getCost() * item.getQuantity();
                }
            }
        }
        return totalCost;
    }

    public int getTotalCalories() {
        int totalCalories = 0;
        for (GranolaItem[] row : itemList) {
            for (GranolaItem item : row) {
                if (item != null) {
                    totalCalories += item.getCalories() * item.getQuantity();
                }
            }
        }
        return totalCalories;
    }

    public int getQuantity(int row, int col) {
        GranolaItem item = this.itemList[row][col];
        return (item != null) ? item.getQuantity() : 0;
    }

    public void initializeItems() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                setItem(i, j, new GranolaItem(NAMES[index], PRICES[index], CALORIES[index], QUANTITIES[index]));
            }
        }
    }

    public void addInventoryObserver(GranolaInventoryObserver observer) {
        observers.add(observer);
    }

    public GranolaItem[][] getItemList() {
        return itemList;
    }
}
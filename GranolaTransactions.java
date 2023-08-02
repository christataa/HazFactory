import java.util.ArrayList;

public class GranolaTransactions {
    private ArrayList<GranolaItem> items;
    private int totalCalories;
    private double totalCost;
    private ArrayList<GranolaTransactionsObserver> observers;

    public GranolaTransactions(ArrayList<GranolaItem> items, int totalCalories, double totalCost) {
        this.items = new ArrayList<>(items);  // create a copy of items to prevent external modification
        this.totalCalories = totalCalories;
        this.totalCost = totalCost;
        this.observers = new ArrayList<>();
    }

    // Getters (and setters if needed)

    public ArrayList<GranolaItem> getItems() {
        return items;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder();
        summary.append("====ORDER SUMMARY====\n");
        for (GranolaItem item : items) {
            summary.append(item.getName() + "\n");
        }
        summary.append("========TOTAL=========\n");
        summary.append("Custom Granola Mix (100g)\n");
        summary.append("Total Calories: " + totalCalories + "\n");
        summary.append("Total Cost: " + totalCost);
        return summary.toString();
    }

    public void completeTransaction() {
        notifyObservers();
    }

    public void addObserver(GranolaTransactionsObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GranolaTransactionsObserver observer : observers) {
            observer.granolaTransactionOccurred(this);
        }
    }
}

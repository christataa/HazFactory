public class GranolaItem {

    private String name;
    private double cost;
    private int calories;
    private int quantity;

    public GranolaItem(String name, double cost, int calories, int quantity) {
        this.name = name;
        this.cost = cost;
        this.calories = calories;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCalories() {
        return calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

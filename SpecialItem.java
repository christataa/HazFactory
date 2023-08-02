public class SpecialItem {
    private String name;
    private double cost;
    private int calories;
    private int quantity;

    public SpecialItem(String name, double cost, int calories) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        if (calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.name = name;
        this.cost = cost;
        this.calories = calories;
        //this.quantity = quantity;
    }

    /*public SpecialItem(String name2, double totalCost, int totalCalories) {
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.cost = cost;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if (calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative.");
        }
        this.calories = calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        if (quantity <= 0) {
            throw new IllegalStateException("Quantity cannot be less than or equal to zero.");
        }
        quantity--;
    }

    @Override
    public String toString() {
        return "<html>" + name + "<br>Qty: " + quantity + "<br>Price: " + cost + "<br>Calories: " + calories + "</html>";
    }
    
}

public class Item {
    private String name;
    private int quantity;
    private double cost;
    private int calories;

    public Item(String name, int quantity, double cost, int calories) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        if (calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative.");
        }
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    public void decreaseQuantity() {
        if(quantity > 0) {
            quantity--;
        } else {
            throw new IllegalStateException("Cannot decrease quantity. Item is out of stock.");
        }
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

    public int getRow() {
        return 0;
    }


}

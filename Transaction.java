public class Transaction {
    private String itemName;
    private double itemCost;
    private double paymentGiven;
    private double changeGiven;

    public Transaction(String itemName, double itemCost, double paymentGiven, double changeGiven) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.paymentGiven = paymentGiven;
        this.changeGiven = changeGiven;
    }

    // getters

    public String getItemName() {
        return itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public double getPaymentGiven() {
        return paymentGiven;
    }

    public double getChangeGiven() {
        return changeGiven;
    }
}

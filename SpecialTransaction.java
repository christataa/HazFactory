public class SpecialTransaction {
    private SpecialItem item;
    private double paymentGiven;
    private double changeGiven;
    private GranolaTransactions granolaTransaction;
    private boolean isGranolaTransaction;

    public SpecialTransaction(SpecialItem item, double paymentGiven, double changeGiven, SpecialItem selectedItem) {
        this.item = selectedItem;
        this.paymentGiven = paymentGiven;
        this.changeGiven = changeGiven;
        this.isGranolaTransaction = false; // Set to false since this constructor is for a SpecialItem transaction
    }

    public SpecialTransaction(GranolaTransactions granolaTransaction, double paymentGiven, double changeGiven) {
        this.granolaTransaction = granolaTransaction;
        this.paymentGiven = paymentGiven;
        this.changeGiven = changeGiven;
        this.isGranolaTransaction = true; // Set to true since this constructor is for a GranolaTransaction
    }

    // getters

    public SpecialItem getItem() {
        return item;
    }

    public double getPaymentGiven() {
        return paymentGiven;
    }

    public double getChangeGiven() {
        return changeGiven;
    }

    public GranolaTransactions getGranolaTransaction() {
        return granolaTransaction;
    }

    public boolean getIsGranolaTransaction() {
        return isGranolaTransaction;
    }
}

public class SpecialPaymentController {
    private SpecialPaymentPanel paymentPanel;
    private SpecialItem selectedItem;

    public SpecialPaymentController(SpecialRestockModel restockModel, SpecialPaymentPanel paymentPanel) {
        this.paymentPanel = paymentPanel;

        // Action listener for the pay button
        paymentPanel.getPayButton().addActionListener(e -> {
            if (selectedItem != null) {
                double sum = paymentPanel.getSum();
                if (sum >= selectedItem.getCost()) {
                    double change = sum - selectedItem.getCost();
                    selectedItem.decreaseQuantity(); // Decrease the quantity of the selected item
                    paymentPanel.getProductInfoArea().setText("You have bought " + selectedItem.getName() + ". Your change is " + change);
                    paymentPanel.resetSum(); // Reset the sum for the next transaction
                    restockModel.notifyObservers(); // Notify observers that the inventory has changed
                } else {
                    paymentPanel.getProductInfoArea().setText("Not enough money inserted. Please insert more.");
                }
            }
        });
    }

    public void setSelectedItem(SpecialItem item) {
        this.selectedItem = item;
        // Display the selected item info in the payment panel
        paymentPanel.getProductInfoArea().setText("Selected Item: " + item.getName() + " Cost: " + item.getCost());
        // Only enable the pay button when the user has inserted enough money
        paymentPanel.getPayButton().setEnabled(paymentPanel.getSum() >= item.getCost());
    }
}

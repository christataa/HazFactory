import javax.swing.*;
import java.awt.*;

public class SpecialPaymentPanel extends JPanel implements InventoryObserver, SpecialInventoryObserver {
    private double sum = 0.0;
    private JTextArea paymentBox;
    private JButton payButton;
    private JTextArea productInfoArea;
    private SpecialRestockModel restockModel;
    
    public SpecialPaymentPanel(SpecialVendingMachinePanel vendingMachinePanel, SpecialRestockModel restockModel, SpecialTransactionsModel transactionsModel) {
        this.restockModel = restockModel;
        this.restockModel.addObserver(this);
        setLayout(new BorderLayout());

        productInfoArea = new JTextArea();
        productInfoArea.setEditable(false);
        add(productInfoArea, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel(new GridLayout(6, 2));
        double[] denominations = {0.01, 0.05, 0.25, 1, 5, 10, 20, 50, 100, 200, 500, 1000};
        JButton[] moneyButtons = new JButton[12];
        for (int i = 0; i < 12; i++) {
            moneyButtons[i] = new JButton(new ImageIcon("Money" + (i+1) + ".png"));
            int index = i;
            moneyButtons[i].addActionListener(e -> {
                sum += denominations[index];
                paymentBox.setText("CASH: " + sum);
            });
            middlePanel.add(moneyButtons[i]);
        }
        add(middlePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        paymentBox = new JTextArea();
        paymentBox.setEditable(false);
        bottomPanel.add(paymentBox, BorderLayout.NORTH);

        payButton = new JButton("Pay");
        payButton.addActionListener(e -> {
            SpecialItem selectedItem = vendingMachinePanel.getSelectedItem();
            if (selectedItem != null) {
                if (sum >= selectedItem.getCost()) {
                    double paymentGiven = sum;
                    sum -= selectedItem.getCost();
                    paymentBox.setText("CASH: " + sum);
                    productInfoArea.setText("Change: " + sum + "\nEnjoy your " + selectedItem.getName());
    
                    int row = vendingMachinePanel.getSelectedItemRow();
                    int col = vendingMachinePanel.getSelectedItemCol();
                    restockModel.decreaseItemQuantity(row, col);
                    restockModel.notifyObservers();
    
                    double changeGiven = paymentGiven - selectedItem.getCost();
                    transactionsModel.addTransaction(new SpecialTransaction(selectedItem, paymentGiven, changeGiven, selectedItem));
                    inventoryChanged();
                    this.setVisible(false);
                    
                    // Clear the product info area after processing the transaction
                    productInfoArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough money.");
                }
            }
        });
        bottomPanel.add(payButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public SpecialPaymentPanel(LayoutManager specialVendingMachinePanel, boolean specialRstockModel) {
    }

    public void updateProductInfo(SpecialItem specialItem) {
        if (specialItem != null) {
            productInfoArea.setText("Selected Item: " + specialItem.getName() + "\nCost: " + specialItem.getCost());
        }
    }

    public JButton getPayButton() {
        return payButton;
    }

    public JTextArea getProductInfoArea() {
        return productInfoArea;
    }

    public double getSum() {
        return sum;
    }

    public void resetSum() {
        sum = 0.0;
        paymentBox.setText("CASH: " + sum);
    }

    @Override
    public void inventoryChanged() {
        this.resetSum();
        productInfoArea.setText("");
    }

    public void updateProductInfo(GranolaItem granolaItem) {
        if (granolaItem != null) {
            productInfoArea.setText("Selected Item: " + granolaItem.getName() + "\nCost: " + granolaItem.getCost());
        }
    }
    
}

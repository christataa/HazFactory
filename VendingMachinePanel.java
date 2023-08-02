import javax.swing.*;
import java.awt.*;

public class VendingMachinePanel extends JPanel implements InventoryObserver {
    private RestockModel restockModel;
    private JButton[][] itemButtons = new JButton[3][3];
    private JTextArea infoArea;
    private JButton selectButton;
    private PaymentPanel paymentPanel;
    private Item selectedItem;
    private int selectedItemRow;
    private int selectedItemCol;

    public VendingMachinePanel(RestockModel restockModel, TransactionsModel transactionsModel) {
        this.restockModel = restockModel;
        this.restockModel.addObserver(this);
        this.setLayout(new BorderLayout());

        // Add title to the north panel
        JLabel title = new JLabel("VENDING MACHINE", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("Empty");
                itemButtons[i][j] = button;
                gridPanel.add(button);
                final int row = i, col = j;
                button.addActionListener(e -> {
                    setSelectedItem(row, col);
                });
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            if (selectedItem != null) {
                paymentPanel.setVisible(true);
                paymentPanel.updateProductInfo(selectedItem);
            }
        });

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(selectButton, BorderLayout.WEST);
        southPanel.add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // Create and add PaymentPanel
        paymentPanel = new PaymentPanel(this, restockModel, transactionsModel); // updated to pass transactionsModel
        add(paymentPanel, BorderLayout.EAST);

        // Initially, the payment panel should not be visible
       // paymentPanel.setVisible(false);

        inventoryChanged();
    }

    @Override
    public void inventoryChanged() {
        Item[][] items = restockModel.getItems();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Item item = items[i][j];
                if (item != null) {
                    itemButtons[i][j].setText("<html>Name: " + item.getName() + "<br>Quantity: " + item.getQuantity() + "<br>Cost: " + item.getCost() + "<br>Calories: " + item.getCalories() + "</html>");
                } else {
                    itemButtons[i][j].setText("Empty");
                }
            }
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public PaymentPanel getPaymentPanel() {
        return paymentPanel;
    }

    public JTextArea getInfoArea() {
        return infoArea;
    }

    public JButton[][] getItemButtons() {
        return itemButtons;
    }

    public void setSelectedItem(int row, int col) {
        Item item = restockModel.getItems()[row][col];
        selectedItem = item;
        if (item != null) {
            infoArea.setText("Name: " + item.getName() + "\tCost: " + item.getCost());
            selectedItemRow = row;
            selectedItemCol = col;
        }
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public int getSelectedItemRow() {
        return selectedItemRow;
    }

    public int getSelectedItemCol() {
        return selectedItemCol;
    }
}

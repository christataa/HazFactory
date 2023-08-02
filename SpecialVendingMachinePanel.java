import javax.swing.*;
import java.awt.*;

public class SpecialVendingMachinePanel extends JPanel implements SpecialInventoryObserver {
    private SpecialRestockModel restockModel;
    private JButton[][] itemButtons = new JButton[3][3];
    private JTextArea infoArea;
    private JButton selectButton;
    private SpecialPaymentPanel paymentPanel;
    private SpecialItem selectedItem;
    private int selectedItemRow;
    private int selectedItemCol;
    private JButton granolaButton;

    public SpecialVendingMachinePanel(SpecialRestockModel restockModel, SpecialTransactionsModel transactionsModel) {
        this.restockModel = restockModel;
        this.restockModel.addObserver(this);
        this.setLayout(new BorderLayout());

        // Add title to the north panel
        JLabel title = new JLabel("SPECIAL VENDING MACHINE", SwingConstants.CENTER);
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

        granolaButton = new JButton("Create Custom Granola Mix");
        granolaButton.addActionListener(e -> {
            JFrame granolaFrame = new JFrame("Create Custom Granola Mix");
            GranolaPanel granolaPanel = new GranolaPanel();

            granolaFrame.add(granolaPanel);
            granolaFrame.pack();
            granolaFrame.setVisible(true);
            granolaFrame.setSize(750,800);
            granolaFrame.setResizable(false);
        });

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(selectButton, BorderLayout.WEST);
        southPanel.add(scrollPane, BorderLayout.CENTER);
        southPanel.add(granolaButton, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        // Create and add SpecialPaymentPanel
        paymentPanel = new SpecialPaymentPanel(this, restockModel, transactionsModel);
        add(paymentPanel, BorderLayout.EAST);

        // Initially, the payment panel should not be visible
        paymentPanel.setVisible(false);

        inventoryChanged();
    }
    public SpecialVendingMachinePanel() {
    }

    @Override
    public void inventoryChanged() {
        SpecialItem[][] items = restockModel.getItems();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SpecialItem item = items[i][j];
                if (item != null) {
                    itemButtons[i][j].setText("<html>Name: " + item.getName() + "<br>Quantity: " + restockModel.getQuantity(i, j) + "<br>Cost: " + item.getCost() + "<br>Calories: " + item.getCalories() + "</html>");
                } else {
                    itemButtons[i][j].setText("Empty");
                }
            }
        }
    }

    public SpecialItem getSelectedItem() {
        return selectedItem;
    }

    public SpecialPaymentPanel getPaymentPanel() {
        return paymentPanel;
    }

    public JTextArea getInfoArea() {
        return infoArea;
    }

    public JButton[][] getItemButtons() {
        return itemButtons;
    }

    public SpecialItem setSelectedItem(int row, int col) {
        SpecialItem item = restockModel.getItem(row, col);
        selectedItem = item;
        if (item != null) {
            infoArea.setText("Name: " + item.getName() + "\tCost: " + item.getCost());
            selectedItemRow = row;
            selectedItemCol = col;
        }
        return item;
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

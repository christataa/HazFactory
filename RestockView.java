import javax.swing.*;
import java.awt.*;

public class RestockView extends JPanel implements InventoryObserver, SpecialInventoryObserver {

    private RestockModel model;
    private JButton[][] buttons = new JButton[3][3];
    private JButton restockButton = new JButton("Restock Item");
    private JButton addItemButton = new JButton("Add Item");
    private JButton deleteButton = new JButton("Delete");
    private JButton changeCostButton = new JButton("Change Cost");

    public RestockView(RestockModel model) {
        this.model = model;

        setLayout(new BorderLayout());

        model.addObserver(this);

        // Add title to the north panel
        JLabel title = new JLabel("RESTOCK", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 3));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton button = new JButton("Empty");
                buttons[i][j] = button;
                panel.add(button);
            }
        }

        add(panel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(restockButton);
        southPanel.add(addItemButton);
        southPanel.add(deleteButton);
        southPanel.add(changeCostButton);

        add(southPanel, BorderLayout.SOUTH);

        // Initially update buttons
        inventoryChanged();
    }

    public JButton getButton(int i, int j) {
        return buttons[i][j];
    }

    public JButton getRestockButton() {
        return restockButton;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getChangeCostButton() {
        return changeCostButton;
    }

    public void updateButton(int row, int col, Item item) {
        if (item != null) {
            buttons[row][col].setText("<html>Name: " + item.getName() + "<br>Quantity: " + item.getQuantity() + "<br>Cost: " + item.getCost() + "<br>Calories: " + item.getCalories() + "</html>");
        } else {
            buttons[row][col].setText("Empty");
        }
    }

    @Override
    public void inventoryChanged() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                updateButton(i, j, model.getItem(i, j));
            }
        }
    }
}

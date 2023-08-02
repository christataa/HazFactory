import javax.swing.*;
import java.awt.*;

public class SpecialViewInventory extends JPanel implements SpecialInventoryObserver, GranolaInventoryObserver {
    private SpecialRestockModel restockModel;
    private GranolaModel granolaModel;
    private JLabel[][] itemLabels = new JLabel[3][6]; // Adjusted for both inventories

    public SpecialViewInventory(SpecialRestockModel restockModel, GranolaModel granolaModel) {
        this.restockModel = restockModel;
        this.granolaModel = granolaModel;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("CURRENT INVENTORY", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.decode("#535878"));
        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.decode("#535878"));
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.decode("#535878"));

        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);

        JPanel inventoryPanel = new JPanel(new GridLayout(4, 6)); // Adjusted for both inventories

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) { // Adjusted for both inventories
                JLabel label = new JLabel("Empty");
                itemLabels[i][j] = label;
                inventoryPanel.add(label);
            }
        }

        add(inventoryPanel, BorderLayout.CENTER);

        restockModel.addObserver(this);
        granolaModel.addObserver(this);
    }

    public void updateInventory() {
        SpecialItem[][] specialItems = restockModel.getItems();
        GranolaItem[][] granolaItems = granolaModel.itemList;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SpecialItem specialItem = specialItems[i][j];
                GranolaItem granolaItem = granolaItems[i][j];
                if (specialItem != null) {
                    int quantity = restockModel.getQuantity(i, j); // get quantity from restockModel
                    itemLabels[i][j].setText("<html>Name: " + specialItem.getName() + "<br>Quantity: " + quantity + "<br>Cost: " + specialItem.getCost() + "<br>Calories: " + specialItem.getCalories() + "</html>");
                } else {
                    itemLabels[i][j].setText("Empty");
                }
                if (granolaItem != null) {
                    int quantity = granolaModel.getQuantity(i, j); // get quantity from granolaModel
                    itemLabels[i][j+3].setText("<html>Name: " + granolaItem.getName() + "<br>Quantity: " + quantity + "<br>Cost: " + granolaItem.getCost() + "<br>Calories: " + granolaItem.getCalories() + "</html>");
                } else {
                    itemLabels[i][j+3].setText("Empty");
                }
            }
        }
    
        revalidate();
        repaint();
    }

    @Override
    public void inventoryChanged() {
        updateInventory();
    }

    @Override
    public void granolaInventoryChanged() { // If GranolaInventoryObserver has a different method, change this
        updateInventory();
    }
}

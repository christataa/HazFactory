import javax.swing.*;
import java.awt.*;

public class ViewInventory extends JPanel implements InventoryObserver, ReplenishObserver {
    private RestockModel restockModel;
    private ReplenishPanel moneyPanel;
    private JLabel[][] itemLabels = new JLabel[3][3];
    private JLabel[] moneyLabels = new JLabel[12];

    public ViewInventory(RestockModel restockModel, ReplenishPanel moneyPanel) {
        this.restockModel = restockModel;
        this.moneyPanel = moneyPanel;

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

        JPanel inventoryPanel = new JPanel(new GridLayout(4, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel("Empty");
                itemLabels[i][j] = label;
                inventoryPanel.add(label);
            }
        }

        for (int i = 0; i < 12; i++) {
            ImageIcon icon = new ImageIcon("Money" + (i + 1) + ".png");
            JLabel label = new JLabel(icon);
            label.setText("Quantity: 0");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            moneyLabels[i] = label;
            inventoryPanel.add(label);
        }

        add(inventoryPanel, BorderLayout.CENTER);
        
        restockModel.addObserver(this);
        moneyPanel.addObserver(this); 
    }

    public void updateInventory() {
        Item[][] items = restockModel.getItems();
        int[] quantities = moneyPanel.getQuantities();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Item item = items[i][j];
                if (item != null) {
                    itemLabels[i][j].setText("<html>Name: " + item.getName() + "<br>Quantity: " + item.getQuantity() + "<br>Cost: " + item.getCost() + "<br>Calories: " + item.getCalories() + "</html>");
                } else {
                    itemLabels[i][j].setText("Empty");
                }
            }
        }

        for (int i = 0; i < 12; i++) {
            moneyLabels[i].setText("Quantity: " + quantities[i]);
        }

        revalidate();
        repaint();
    }

    @Override
    public void inventoryChanged() {
        updateInventory();
    }

    @Override
    public void moneyChanged() {
        updateInventory();
    }
}

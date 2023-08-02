import javax.swing.*;
import java.awt.*;

public class GranolaRestock extends JPanel implements GranolaInventoryObserver {

    private GranolaModel model;
    private JButton[][] buttons = new JButton[3][3];
    private JButton restockButton = new JButton("Restock Item");
    private JButton changeCostButton = new JButton("Change Cost");

    public GranolaRestock(GranolaModel model) {
        this.model = model;
        model.addInventoryObserver(this);

        setLayout(new BorderLayout());

        // Add title to the north panel
        JLabel title = new JLabel("RESTOCK GRANOLA INGREDIENT", SwingConstants.CENTER);
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
                JButton button = new JButton("No item here");
                buttons[i][j] = button;
                panel.add(button);
            }
        }

        add(panel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(restockButton);
        southPanel.add(changeCostButton);

        add(southPanel, BorderLayout.SOUTH);

        // Initially update buttons
        granolaInventoryChanged();
    }

    public JButton getButton(int i, int j) {
        return buttons[i][j];
    }

    public JButton getRestockButton() {
        return restockButton;
    }

    public JButton getChangeCostButton() {
        return changeCostButton;
    }

    public void updateButton(int row, int col, GranolaItem item) {
        if (item != null) {
            buttons[row][col].setText("<html>Name: " + item.getName() + "<br>Quantity: " + item.getQuantity() + "<br>Cost: " + item.getCost() + "<br>Calories: " + item.getCalories() + "</html>");
        } else {
            buttons[row][col].setText("No item here");
        }
    }

    @Override
    public void granolaInventoryChanged() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                updateButton(i, j, model.getItem(i, j));
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReplenishPanel extends JPanel {
    private JButton[][] buttons = new JButton[4][3];
    private List<ReplenishObserver> observers = new ArrayList<>();
    private JButton replenishButton;
    private MoneyTable moneyTable;

    public ReplenishPanel(MoneyTable moneyTable) {
        this.moneyTable = moneyTable;

        setLayout(new BorderLayout());

        JPanel moneyPanel = new JPanel(new GridLayout(4, 3));
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                ImageIcon icon = new ImageIcon("Money" + (i * 3 + j + 1) + ".png");
                JButton button = new JButton(icon);
                button.setText("Quantity: " + moneyTable.getQuantity(i * 3 + j));
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setVerticalTextPosition(JButton.BOTTOM);
                buttons[i][j] = button;
                moneyPanel.add(button);
            }
        }

        replenishButton = new JButton("Replenish");

        JPanel replenishPanel = new JPanel();
        replenishPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("REPLENISH MONEY FOR CHANGE", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);
        add(moneyPanel, BorderLayout.CENTER);
        add(replenishButton, BorderLayout.SOUTH);
    }

    public JButton getButton(int i, int j) {
        return buttons[i][j];
    }

    public JButton getReplenishButton() {
        return replenishButton;
    }

    public void addObserver(ReplenishObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ReplenishObserver observer : observers) {
            observer.moneyChanged();
        }
    }

    public void replenish(int index, int quantity) {
        moneyTable.replenishMoney(index, quantity);
        buttons[index / 3][index % 3].setText("Quantity: " + moneyTable.getQuantity(index));
        notifyObservers();
    }

    public int[] getQuantities() {
        int[] quantities = new int[12];
        for (int i = 0; i < 12; i++) {
            quantities[i] = moneyTable.getQuantity(i);
        }
        return quantities;
    }
}

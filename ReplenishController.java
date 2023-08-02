import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ReplenishController {
    private ReplenishPanel replenishPanel;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public ReplenishController(MoneyTable moneyTable, ReplenishPanel replenishPanel) {
        this.replenishPanel = replenishPanel;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                replenishPanel.getButton(i, j).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedRow = finalI;
                        selectedCol = finalJ;
                    }
                });
            }
        }

        replenishPanel.getReplenishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replenishMoney();
            }
        });
    }

    private void replenishMoney() {
        if (selectedRow != -1 && selectedCol != -1) {
            String quantityStr = JOptionPane.showInputDialog("Enter quantity to replenish:");
            int quantity = Integer.parseInt(quantityStr);
            int index = selectedRow * 3 + selectedCol;
            replenishPanel.replenish(index, quantity);
        }
    }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VendingMachineController {

    public VendingMachineController(VendingMachinePanel vendingMachinePanel, PaymentPanel paymentPanel) {
        // Add action listeners to buttons in VendingMachinePanel
        JButton[][] itemButtons = vendingMachinePanel.getItemButtons();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = itemButtons[i][j];
                final int row = i, col = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vendingMachinePanel.setSelectedItem(row, col);
                        paymentPanel.updateProductInfo(vendingMachinePanel.getSelectedItem());
                    }
                });
            }
        }

        // Add action listener to select button in VendingMachinePanel
        vendingMachinePanel.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendingMachinePanel.getSelectedItem() != null) {
                    paymentPanel.setVisible(true);
                }
            }
        });
    }

    public VendingMachineController(RestockModel restockModel, VendingMachinePanel vendingMachinePanel2,
            PaymentController paymentController) {
    }
}

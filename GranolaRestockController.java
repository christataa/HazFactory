import javax.swing.*;

public class GranolaRestockController {
    private GranolaModel model;
    private GranolaRestock view;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public GranolaRestockController(GranolaModel model, GranolaRestock view, GranolaPanel vendingMachine) {
        this.model = model;
        this.view = view;
        model.addObserver(view);

        setupController();
    }

    private void setupController() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                view.getButton(i, j).addActionListener(e -> {
                    selectedRow = finalI;
                    selectedCol = finalJ;
                    System.out.println("Button at (" + selectedRow + ", " + selectedCol + ") is selected");
                });
            }
        }
    
        view.getRestockButton().addActionListener(e -> {
            System.out.println("Restock button clicked");
            restockItem();
        });
    
        view.getChangeCostButton().addActionListener(e -> {
            System.out.println("Change cost button clicked");
            changeCost();
        });
    }
    
    private void restockItem() {
        if (selectedRow != -1 && selectedCol != -1) {
            String quantityStr = JOptionPane.showInputDialog("Enter quantity to restock:");
            int quantity = Integer.parseInt(quantityStr);
            model.restockItem(selectedRow, selectedCol, quantity);
        }
    }

    private void changeCost() {
        if (selectedRow != -1 && selectedCol != -1) {
            String costStr = JOptionPane.showInputDialog("Enter new cost:");
            double cost = Double.parseDouble(costStr);
            model.changeItemCost(selectedRow, selectedCol, cost);
        }
    }
}

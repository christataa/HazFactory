import javax.swing.*;

public class SpecialRestockController {
    private SpecialRestockModel model;
    private SpecialRestockView view;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SpecialRestockController(SpecialRestockModel model, SpecialRestockView view, SpecialVendingMachinePanel vendingMachine) {
        this.model = model;
        this.view = view;
        model.addObserver(view);
        model.addObserver(vendingMachine);

        setupController();
    }

    public SpecialRestockController(SpecialRestockView specialRestockView, SpecialRestockModel specialRestockModel) {
    }

    private void setupController() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                view.getButton(i, j).addActionListener(e -> {
                    selectedRow = finalI;
                    selectedCol = finalJ;
                });
            }
        }
        
        view.getRestockButton().addActionListener(e -> restockItem());
        view.getAddItemButton().addActionListener(e -> addItem());
        view.getDeleteButton().addActionListener(e -> deleteItem());
        view.getChangeCostButton().addActionListener(e -> changeCost());
    }

    private void restockItem() {
        if (selectedRow != -1 && selectedCol != -1 && model.getItem(selectedRow, selectedCol) != null) {
            String quantityStr = JOptionPane.showInputDialog("Enter quantity to restock:");
            int quantity = Integer.parseInt(quantityStr);
            model.restockItem(selectedRow, selectedCol, quantity);
        }
    }

    private void addItem() {
        if (selectedRow != -1 && selectedCol != -1) {
            String name = JOptionPane.showInputDialog("Enter item name:");
            String costStr = JOptionPane.showInputDialog("Enter item cost:");
            double cost = Double.parseDouble(costStr);
            String caloriesStr = JOptionPane.showInputDialog("Enter item calories:");
            int calories = Integer.parseInt(caloriesStr);
            String quantityStr = JOptionPane.showInputDialog("Enter item quantity:");
            int quantity = Integer.parseInt(quantityStr);
            model.setItem(selectedRow, selectedCol, new SpecialItem(name, cost, calories), quantity);
        }
    }

    private void deleteItem() {
        if (selectedRow != -1 && selectedCol != -1) {
            model.deleteItem(selectedRow, selectedCol);
        }
    }

    private void changeCost() {
        if (selectedRow != -1 && selectedCol != -1 && model.getItem(selectedRow, selectedCol) != null) {
            String costStr = JOptionPane.showInputDialog("Enter new cost:");
            double cost = Double.parseDouble(costStr);
            model.changeItemCost(selectedRow, selectedCol, cost);
        }
    }
}

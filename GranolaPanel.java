import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GranolaPanel extends JPanel {

    private JTextArea textAreaStack;
    private JButton completeButton;
    private JButton deleteButton;
    private GranolaModel granolaModel;
    private ArrayList<GranolaItem> selectedItems;
    private ArrayList<GranolaTransactions> transactions;
    private SpecialTransactionsModel transactionsModel;



    public GranolaPanel() {
        this.transactionsModel = new SpecialTransactionsModel();
        this.transactions = new ArrayList<>();
        this.granolaModel = new GranolaModel();
        this.selectedItems = new ArrayList<>();
        this.textAreaStack = new JTextArea();
        textAreaStack.setEditable(false);

        granolaModel.initializeItems();

        setLayout(new BorderLayout());

        // Add title to the north panel
        JLabel title = new JLabel("CREATE CUSTOM GRANOLA MIX", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GranolaItem item = granolaModel.getItem(i, j);
                String name = item.getName();
                double price = item.getCost();
                int calorie = item.getCalories();
                int quantity = item.getQuantity();

                JButton button = new JButton("<html>Name: " + name + "<br>Quantity: " + quantity + "<br>Price: " + price + "<br>Calories: " + calorie + " kcal ");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if (selectedItems.size() < 5) {
                        selectedItems.add(granolaModel.getItem(finalI, finalJ));
                        textAreaStack.append("Name: " + name + "\nPrice: " + price + "\nCalories: " + calorie + " kcal\n---------------------------\n");
                        if (selectedItems.size() == 5) {
                            completeButton.setVisible(true);
                        }
                    }
                });
                gridPanel.add(button);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(textAreaStack, BorderLayout.CENTER);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            if (!selectedItems.isEmpty()) {
                selectedItems.remove(selectedItems.size() - 1);
                textAreaStack.setText(""); // clear the text area
                for (GranolaItem item : selectedItems) { // iterate over the remaining items
                    textAreaStack.append("Name: " + item.getName() + "\\nPrice: " + item.getCost() + "\\nCalories: " + item.getCalories() + " kcal\\n---------------------------\\n"); // append each item's information
                }
                completeButton.setVisible(selectedItems.size() == 5);
            }
        });

        completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            if (selectedItems.size() == 5) {
                double totalCost = selectedItems.stream().mapToDouble(GranolaItem::getCost).sum();
                int totalCalories = selectedItems.stream().mapToInt(GranolaItem::getCalories).sum();

                GranolaTransactions transaction = new GranolaTransactions(selectedItems, totalCalories, totalCost);
                transactions.add(transaction);
                
                transaction.completeTransaction();


                StringBuilder summary = new StringBuilder();
                summary.append("====ORDER SUMMARY====\n");
                for (GranolaItem item : selectedItems) {
                    summary.append(item.getName() + "\n");
                }
                summary.append("========TOTAL=========\n");
                summary.append("Custom Granola Mix (100g)\n");
                summary.append("Total Calories: " + totalCalories + "\n");
                summary.append("Total Cost: " + totalCost);

                Object[] options = {"Next"};
                int selectedOption = JOptionPane.showOptionDialog(this, summary.toString(), "Order Summary", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (selectedOption == 0) {  // if "Next" is clicked
                    JOptionPane.showMessageDialog(this, "Preparing Ingredients....", "Process", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "Dispensing Ingredients...", "Process", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "Mixing Ingredients...", "Process", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "Packaging Granola mix...", "Process", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "Custom Granola Mix is ready!", "Process", JOptionPane.INFORMATION_MESSAGE);
                
                    GranolaTransactions granolaTransaction = new GranolaTransactions(selectedItems, totalCalories, totalCost);
                    transactions.add(granolaTransaction);
                    granolaTransaction.completeTransaction();
                    transactionsModel.addTransaction(new SpecialTransaction(granolaTransaction, totalCost, 0));
                }

                selectedItems.clear();
                completeButton.setVisible(false);
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(deleteButton);
        southPanel.add(completeButton);

        eastPanel.add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);

        completeButton.setVisible(false);
    }
    public ArrayList<GranolaTransactions> getTransactions() {
        return transactions;
    }
}


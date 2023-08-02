import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainMenu extends JFrame {
    private JPanel cardPanel;
    private CardLayout cl;

    public MainMenu() {
        setTitle("HAZ FACTORY");
        setSize(750, 800);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        getContentPane().add(mainPanel);

        JMenuBar menuBar = new JMenuBar();

        JMenu regularMenu = new JMenu("Create Regular Vending Machine");
        JMenuItem vendingMachineFeatures = new JMenuItem("Vending Machine Features");
        regularMenu.add(vendingMachineFeatures);

        JMenu maintenanceMenu = new JMenu("Maintenance Features");
        JMenuItem editInventory = new JMenuItem("Edit Product Inventory");
        maintenanceMenu.add(editInventory);

        JMenuItem replenishMoney = new JMenuItem("Edit Money Inventory");
        maintenanceMenu.add(replenishMoney);

        JMenuItem viewInventory = new JMenuItem("View Current Inventory");
        maintenanceMenu.add(viewInventory);

        JMenuItem viewTransactionSummary = new JMenuItem("View Transaction Summary");
        maintenanceMenu.add(viewTransactionSummary);

        regularMenu.add(maintenanceMenu);
        menuBar.add(regularMenu);

        JMenu specialMenu = new JMenu("Create Special Vending Machine");
        JMenuItem testVendingMachineFeatures = new JMenuItem("Test Vending Machine Features");
        specialMenu.add(testVendingMachineFeatures);

        JMenu testMaintenanceFeatures = new JMenu("Test Maintenance Features");
        JMenuItem testEditProductInventory = new JMenuItem("Edit Product Inventory");
        testMaintenanceFeatures.add(testEditProductInventory);

        JMenuItem editSpecialInventory = new JMenuItem("Edit Special Products Inventory");
        testMaintenanceFeatures.add(editSpecialInventory);

        JMenuItem testEditMoneyInventory = new JMenuItem("Edit Money Inventory");
        testMaintenanceFeatures.add(testEditMoneyInventory);

        JMenuItem testViewCurrentInventory = new JMenuItem("View Current Inventory");
        testMaintenanceFeatures.add(testViewCurrentInventory);

        JMenuItem testViewTransactionSummary = new JMenuItem("View Transaction Summary");
        testMaintenanceFeatures.add(testViewTransactionSummary);

        specialMenu.add(testMaintenanceFeatures);
        menuBar.add(specialMenu);

        setJMenuBar(menuBar);

        regularMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                specialMenu.setEnabled(false);
            }
            public void menuDeselected(MenuEvent e) {
            }
            public void menuCanceled(MenuEvent e) {
            }
        });

        specialMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                regularMenu.setEnabled(false);
            }
            public void menuDeselected(MenuEvent e) {
            }
            public void menuCanceled(MenuEvent e) {
            }
        });

        cardPanel = new JPanel();
        cl = new CardLayout();
        cardPanel.setLayout(cl);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regularMenu.setEnabled(true);
                specialMenu.setEnabled(true);
                cl.show(cardPanel, "WELCOME");
            }
        });
        mainPanel.add(backButton, BorderLayout.SOUTH);

        WelcomePanel welcome = new WelcomePanel();
        cardPanel.add(welcome, "WELCOME");

        RestockModel restockModel = new RestockModel();
        TransactionsModel transactionsModel = new TransactionsModel();
        RestockView restockView = new RestockView(restockModel);
        restockModel.addObserver(restockView);

        VendingMachinePanel vendingMachinePanel = new VendingMachinePanel(restockModel, transactionsModel);
        restockModel.addObserver(vendingMachinePanel);

        PaymentPanel paymentPanel = vendingMachinePanel.getPaymentPanel();
        PaymentController paymentController = new PaymentController(restockModel, paymentPanel);

        new VendingMachineController(restockModel, vendingMachinePanel, paymentController);
        new RestockController(restockModel, restockView, vendingMachinePanel);

        cardPanel.add(vendingMachinePanel, "VENDING_MACHINE");
        vendingMachineFeatures.addActionListener(e -> cl.show(cardPanel, "VENDING_MACHINE")); 

        cardPanel.add(restockView, "RESTOCK");
        editInventory.addActionListener(e -> cl.show(cardPanel, "RESTOCK"));

        MoneyTable moneyTable = new MoneyTable();
        ReplenishPanel replenishPanel = new ReplenishPanel(moneyTable);
        new ReplenishController(moneyTable, replenishPanel);
        cardPanel.add(replenishPanel, "REPLENISH");
        replenishMoney.addActionListener(e -> cl.show(cardPanel, "REPLENISH"));

        testEditMoneyInventory.addActionListener(e -> cl.show(cardPanel, "REPLENISH"));

        ViewInventory viewInventoryPanel = new ViewInventory(restockModel, replenishPanel);
        cardPanel.add(viewInventoryPanel, "INVENTORY");
        viewInventory.addActionListener(e -> cl.show(cardPanel, "INVENTORY"));

        ViewTransactionsPanel transactionPanel = new ViewTransactionsPanel(transactionsModel);
        cardPanel.add(transactionPanel, "TRANSACTION");
        viewTransactionSummary.addActionListener(e -> cl.show(cardPanel, "TRANSACTION"));
        GranolaModel granolaModel = new GranolaModel();

        GranolaRestock granolaRestockPanel = new GranolaRestock(granolaModel);
        cardPanel.add(granolaRestockPanel, "GRANOLA_RESTOCK");
        editSpecialInventory.addActionListener(e -> cl.show(cardPanel, "GRANOLA_RESTOCK"));

        SpecialRestockModel specialRestockModel = new SpecialRestockModel();
        SpecialRestockView specialRestockView = new SpecialRestockView(specialRestockModel);
        SpecialTransactionsModel specialTransactionsModel = new SpecialTransactionsModel();
        SpecialVendingMachinePanel specialVendingMachinePanel = new SpecialVendingMachinePanel(specialRestockModel, specialTransactionsModel);
        specialRestockModel.addObserver(specialVendingMachinePanel);

        SpecialPaymentPanel specialPaymentPanel = specialVendingMachinePanel.getPaymentPanel();
        SpecialPaymentController specialPaymentController = new SpecialPaymentController(specialRestockModel, specialPaymentPanel);


        new SpecialRestockController(specialRestockModel, specialRestockView, specialVendingMachinePanel);
        new SpecialVendingMachineController(specialRestockModel, specialVendingMachinePanel, specialPaymentController);

        cardPanel.add(specialRestockView, "SPECIAL_RESTOCK");        
        new SpecialRestockController(specialRestockView, specialRestockModel);

        new SpecialVendingMachineController(specialRestockModel, specialVendingMachinePanel, specialPaymentController);

        cardPanel.add(specialVendingMachinePanel, "SPECIAL_VENDING_MACHINE");
        testVendingMachineFeatures.addActionListener(e -> cl.show(cardPanel, "SPECIAL_VENDING_MACHINE"));

        SpecialViewInventory specialViewInventoryPanel = new SpecialViewInventory(specialRestockModel, granolaModel);
        cardPanel.add(specialViewInventoryPanel, "SPECIAL_INVENTORY");
        testViewCurrentInventory.addActionListener(e -> cl.show(cardPanel, "SPECIAL_INVENTORY"));

        SpecialViewTransactionsPanel specialTransactionPanel = new SpecialViewTransactionsPanel(specialTransactionsModel);
        cardPanel.add(specialTransactionPanel, "SPECIAL_TRANSACTION");
        testViewTransactionSummary.addActionListener(e -> cl.show(cardPanel, "SPECIAL_TRANSACTION"));
        testEditProductInventory.addActionListener(e -> cl.show(cardPanel, "SPECIAL_RESTOCK"));

        // Assuming GranolaRestock class has a default constructor
        //GranolaModel granolaModel = new GranolaModel();
        //GranolaRestock granolaRestockPanel = new GranolaRestock(granolaModel);
        cardPanel.add(granolaRestockPanel, "GRANOLA_RESTOCK");
        editSpecialInventory.addActionListener(e -> cl.show(cardPanel, "GRANOLA_RESTOCK"));

        cl.show(cardPanel, "WELCOME");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu frame = new MainMenu();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


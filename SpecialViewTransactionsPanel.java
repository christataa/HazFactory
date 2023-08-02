import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class SpecialViewTransactionsPanel extends JPanel implements SpecialTransactionsObserver {
    private SpecialTransactionsModel transactionsModel;
    private JTextArea specialTransactionsArea = new JTextArea();
    private JTextArea granolaTransactionsArea = new JTextArea();

    public SpecialViewTransactionsPanel(SpecialTransactionsModel transactionsModel) {
        this.transactionsModel = transactionsModel;
        transactionsModel.addObserver(this);
        setLayout(new BorderLayout());

        // Create a panel to hold two text areas
        JPanel textAreasPanel = new JPanel();
        textAreasPanel.setLayout(new BoxLayout(textAreasPanel, BoxLayout.X_AXIS));
        textAreasPanel.add(new JScrollPane(specialTransactionsArea));
        textAreasPanel.add(new JScrollPane(granolaTransactionsArea));
        add(textAreasPanel, BorderLayout.CENTER);

        transactionsChanged();

        // Add title to the north panel
        JLabel title = new JLabel("TRANSACTION SUMMARY", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(Color.decode("#9DB0CE"));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.decode("#535878"));
        titlePanel.add(title, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

    @Override
    public void transactionsChanged() {
        StringBuilder sbSpecial = new StringBuilder();
        StringBuilder sbGranola = new StringBuilder();
    
        for (SpecialTransaction transaction : transactionsModel.getTransactions()) {
            if (transaction.getIsGranolaTransaction()) {
                GranolaTransactions granolaTrans = transaction.getGranolaTransaction();
                if (granolaTrans != null) {
                    sbGranola.append(granolaTrans.toString());
                }
            } else {
                sbSpecial.append("Item: ").append(transaction.getItem().getName()).append("\n");
                sbSpecial.append("Cost: ").append(transaction.getItem().getCost()).append("\n");
                sbSpecial.append("Payment: ").append(transaction.getPaymentGiven()).append("\n");
                sbSpecial.append("Change: ").append(transaction.getChangeGiven()).append("\n");
                sbSpecial.append("------------\n");
            }
        }
    
        specialTransactionsArea.setText(sbSpecial.toString());
        granolaTransactionsArea.setText(sbGranola.toString());
    }
    
}


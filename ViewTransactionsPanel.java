import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ViewTransactionsPanel extends JPanel implements TransactionsObserver {
    private TransactionsModel transactionsModel;
    private JTextArea transactionsArea = new JTextArea();

    public ViewTransactionsPanel(TransactionsModel transactionsModel) {
        this.transactionsModel = transactionsModel;
        transactionsModel.addObserver(this);
        setLayout(new BorderLayout());
        add(new JScrollPane(transactionsArea), BorderLayout.CENTER);
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
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactionsModel.getTransactions()) {
            sb.append("Item: ").append(transaction.getItemName()).append("\n");
            sb.append("Cost: ").append(transaction.getItemCost()).append("\n");
            sb.append("Payment: ").append(transaction.getPaymentGiven()).append("\n");
            sb.append("Change: ").append(transaction.getChangeGiven()).append("\n");
            sb.append("------------\n");
        }
        transactionsArea.setText(sb.toString());
    }
}

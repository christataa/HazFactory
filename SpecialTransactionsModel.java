import java.util.ArrayList;
import java.util.List;

public class SpecialTransactionsModel implements GranolaTransactionsObserver {
    private List<SpecialTransaction> transactions;
    private List<SpecialTransactionsObserver> observers;

    public SpecialTransactionsModel() {
        this.transactions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addTransaction(SpecialTransaction transaction) {
        transactions.add(transaction);
        notifyObservers();
    }

    public List<SpecialTransaction> getTransactions() {
        return transactions;
    }

    public void addObserver(SpecialTransactionsObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (SpecialTransactionsObserver observer : observers) {
            observer.transactionsChanged();
        }
    }

    @Override
    public void granolaTransactionOccurred(GranolaTransactions transaction) {
        addTransaction(new SpecialTransaction(transaction, transaction.getTotalCost(), 0)); // change values accordingly, e.g. totalCost and change
    }
}

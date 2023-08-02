import java.util.*;

public class TransactionsModel {
    private Stack<Transaction> transactions = new Stack<>();
    private List<TransactionsObserver> observers = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.push(transaction);
        for (TransactionsObserver observer : observers) {
            observer.transactionsChanged();
        }
    }

    public Stack<Transaction> getTransactions() {
        return transactions;
    }

    public void addObserver(TransactionsObserver observer) {
        observers.add(observer);
    }
}

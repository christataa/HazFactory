public class MoneyTable {
    private int[] quantities = new int[12];
    
    public MoneyTable() {}
    
    public int getQuantity(int index) {
        return quantities[index];
    }

    public void replenishMoney(int index, int quantity) {
        quantities[index] += quantity;
    }
}

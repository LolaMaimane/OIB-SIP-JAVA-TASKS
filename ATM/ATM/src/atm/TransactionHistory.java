/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

    import java.util.ArrayList;

public class TransactionHistory {
    private ArrayList<String> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Lola Maimane
 */
public class Transfer {
    public static boolean performTransfer(double amount, Account sender, Account recipient) {
        if (amount > 0 && amount <= sender.getBalance() && sender != recipient) {
            sender.transfer(recipient, amount);
            return true;
        }
        return false;
    }
}

    


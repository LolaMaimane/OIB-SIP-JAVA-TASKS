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

    public class Deposit {
    public static boolean performDeposit(double amount, Account account) {
        if (amount > 0) {
            account.deposit(amount);
            return true;
        }
        return false;
    }
}


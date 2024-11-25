/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transaction;

import core.models.account.Account;

/**
 *
 * @author ALVARO PEREZ
 */
public class DepositTransaction extends Transaction {
    public DepositTransaction(Account destinationAccount, double amount) {
        super(TransactionType.DEPOSIT, null, destinationAccount, amount);
    }

    @Override
    public void execute() {
        if (destinationAccount != null) {
            destinationAccount.deposit(amount);
        }
    }
}
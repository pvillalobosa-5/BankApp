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
public class TransferTransaction extends Transaction {
    public TransferTransaction(Account sourceAccount, Account destinationAccount, double amount) {
        super(TransactionType.TRANSFER, sourceAccount, destinationAccount, amount);
    }

    @Override
    public void execute() {
        if (sourceAccount != null && destinationAccount != null) {
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
        }
    }
}

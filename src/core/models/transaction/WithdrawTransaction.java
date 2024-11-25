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
public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(Account sourceAccount, double amount) {
        super(TransactionType.WITHDRAW, sourceAccount, null, amount);
    }

    @Override
    public void execute() {
        if (sourceAccount != null) {
            sourceAccount.withdraw(amount);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.account;

/**
 *
 * @author ALVARO PEREZ
 */
public class AccountTransactionManager {

    private final Account account;
    private final AccountInterface accountInterface;

    public AccountTransactionManager(Account account, AccountInterface accountInterface) {
        this.account = account;
        this.accountInterface = accountInterface;
    }

    public void deposit(double amount) {
        accountInterface.deposit(account, amount);
    }

    public boolean withdraw(double amount) {
        return accountInterface.withdraw(account, amount);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.account;

/**
 *
 * @author ALVARO PEREZ
 */
public class DefaultAccountManager implements AccountInterface {

    @Override
    public void deposit(Account account, double amount) {
        if (amount > 0) {
            account.deposit(amount);
        }
    }

    @Override
    public boolean withdraw(Account account, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.withdraw(amount);
            return true;
        }
        return false;
    }
}

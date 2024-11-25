/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.account;

/**
 *
 * @author ALVARO PEREZ
 */
public interface AccountInterface {
   void deposit(Account account, double amount);
    boolean withdraw(Account account, double amount);
}

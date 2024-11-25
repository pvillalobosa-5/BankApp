/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.user;

import core.models.account.Account;
import java.util.List;

/**
 *
 * @author ALVARO PEREZ
 */
public interface AccountManager {

    void addAccountToUser(User user, Account account);

    int getNumAccounts(User user);

    List<Account> getAccounts(User user);
}

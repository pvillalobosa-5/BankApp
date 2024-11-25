/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.user;

import core.models.account.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ALVARO PEREZ
 */
public class DefaultAccountManager implements AccountManager {

    private final Map<User, ArrayList<Account>> userAccounts = new HashMap<>();

    @Override
    public void addAccountToUser(User user, Account account) {
        userAccounts.computeIfAbsent(user, k -> new ArrayList<>()).add(account);
    }

    @Override
    public int getNumAccounts(User user) {
        return userAccounts.getOrDefault(user, new ArrayList<>()).size();
    }

    @Override
    public ArrayList<Account> getAccounts(User user) {
        return userAccounts.getOrDefault(user, new ArrayList<>());
    }
}

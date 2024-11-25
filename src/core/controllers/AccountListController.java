/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.account.Account;
import core.models.user.User;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author ALVARO PEREZ
 */
public class AccountListController {

    private static AccountListController instance;
    private final ArrayList<Account> accounts;

    private AccountListController() {
        this.accounts = new ArrayList<>();
    }

    public static AccountListController getInstance() {
        if (instance == null) {
            instance = new AccountListController();
        }
        return instance;
    }

    public Response addAccount(User owner, double initialBalance) {
        if (owner == null) {
            return new Response(Status.BAD_REQUEST, "Owner must be a registered user", null);
        }
        if (initialBalance < 0) {
            return new Response(Status.BAD_REQUEST, "Initial balance cannot be negative", null);
        }

        String id = generateAccountId();
        Account newAccount = new Account(id, owner, initialBalance);
        accounts.add(newAccount);
        return new Response(Status.OK, "Account added successfully", newAccount);
    }

    public Response getAccounts() {
        accounts.sort(Comparator.comparing(Account::getId));
        return new Response(Status.OK, "Accounts retrieved successfully", accounts);
    }

    private String generateAccountId() {
        return "ACC" + (accounts.size() + 1);
    }
}
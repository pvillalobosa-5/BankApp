/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.account.Account;
import core.models.user.User;

/**
 *
 * @author ALVARO PEREZ
 */
import java.util.ArrayList;

public class AccountController {

    private static final ArrayList<Account> accounts = new ArrayList<>();

    public static Account getAccountById(String sourceAccountId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public AccountController() {
    }

    public static Response createAccount(String id, User owner, double initialBalance) {
        if (owner == null) {
            return new Response(Status.BAD_REQUEST, "Owner cannot be null", null);
        }
        if (initialBalance < 0) {
            return new Response(Status.BAD_REQUEST, "Initial balance cannot be negative", null);
        }

        Account account = new Account(id, owner, initialBalance);
        accounts.add(account);
        return new Response(Status.OK, "Account created successfully", account);
    }

    public static int getNumAccounts(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return (int) accounts.stream().filter(account -> account.getOwner().equals(user)).count();
    }
    

    public static Response getAccounts() {
        return new Response(Status.OK, "Accounts retrieved successfully", accounts);
    }
}

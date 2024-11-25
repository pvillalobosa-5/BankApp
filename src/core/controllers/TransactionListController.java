/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.account.Account;
import core.models.transaction.DepositTransaction;
import core.models.transaction.Transaction;
import core.models.transaction.TransactionType;
import core.models.transaction.TransferTransaction;
import java.util.ArrayList;

/**
 *
 * @author ALVARO PEREZ
 */
public class TransactionListController {
    private static TransactionListController instance;
    private final ArrayList<Transaction> transactions;

    private TransactionListController() {
        this.transactions = new ArrayList<>();
    }

    public static TransactionListController getInstance() {
        if (instance == null) {
            instance = new TransactionListController();
        }
        return instance;
    }

    public Response addTransaction(TransactionType type, Account source, Account destination, double amount) {
        if (amount <= 0) {
            return new Response(Status.BAD_REQUEST, "Amount must be greater than zero", null);
        }

        Transaction transaction;
        switch (type) {
            case DEPOSIT:
                if (destination == null) {
                    return new Response(Status.BAD_REQUEST, "Destination account is required", null);
                }
                destination.deposit(amount);
                transaction = new DepositTransaction(destination, amount);
                break;

            case WITHDRAW:
                if (source == null) {
                    return new Response(Status.BAD_REQUEST, "Source account is required", null);
                }
                if (!source.withdraw(amount)) {
                    return new Response(Status.BAD_REQUEST, "Insufficient balance", null);
                }
                transaction = new Transaction(TransactionType.WITHDRAW, source, null, amount) {
                    @Override
                    public void execute() {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                };
                break;

            case TRANSFER:
                if (source == null || destination == null) {
                    return new Response(Status.BAD_REQUEST, "Both source and destination accounts are required", null);
                }
                if (!source.withdraw(amount)) {
                    return new Response(Status.BAD_REQUEST, "Insufficient balance in source account", null);
                }
                destination.deposit(amount);
                transaction = new TransferTransaction(source, destination, amount);
                break;

            default:
                return new Response(Status.BAD_REQUEST, "Invalid transaction type", null);
        }

        transactions.add(transaction);
        return new Response(Status.OK, "Transaction added successfully", transaction);
    }

    public Response getTransactions() {
        return new Response(Status.OK, "Transactions retrieved successfully", transactions);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this licenses
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.transaction.Transaction;
import core.models.transaction.TransactionType;
import java.util.ArrayList;
import java.util.List;
import core.models.account.Account;
import core.models.transaction.DepositTransaction;
import core.models.transaction.TransferTransaction;

/**
 *
 * @author edangulo
 */
import java.util.*;
import java.util.function.BiFunction;

public class TransactionController {

    private final List<Transaction> transactions = new ArrayList<>();
    private final Map<TransactionType, BiFunction<Account[], Double, Response>> transactionHandlers;

    public TransactionController() {
        transactionHandlers = new HashMap<>();
        transactionHandlers.put(TransactionType.DEPOSIT, this::handleDeposit);
        transactionHandlers.put(TransactionType.WITHDRAW, this::handleWithdrawal);
        transactionHandlers.put(TransactionType.TRANSFER, this::handleTransfer);
    }

    public Response executeTransaction(TransactionType type, Account source, Account destination, double amount) {
        if (amount <= 0) {
            return new Response(Status.BAD_REQUEST, "Amount must be greater than zero", null);
        }

        BiFunction<Account[], Double, Response> handler = transactionHandlers.get(type);
        if (handler == null) {
            return new Response(Status.BAD_REQUEST, "Invalid transaction type", null);
        }

        return handler.apply(new Account[]{source, destination}, amount);
    }

    private Response handleDeposit(Account[] accounts, double amount) {
        Account destination = accounts[1];
        if (destination == null) {
            return new Response(Status.BAD_REQUEST, "Destination account is required", null);
        }

        destination.deposit(amount);
        DepositTransaction deposit = new DepositTransaction(destination, amount);
        transactions.add(deposit);

        return new Response(Status.OK, "Deposit successful", deposit);
    }

    private Response handleWithdrawal(Account[] accounts, double amount) {
        Account source = accounts[0];
        if (source == null) {
            return new Response(Status.BAD_REQUEST, "Source account is required", null);
        }
        if (!source.withdraw(amount)) {
            return new Response(Status.BAD_REQUEST, "Insufficient balance", null);
        }

        Transaction withdraw = new Transaction(TransactionType.WITHDRAW, source, null, amount) {
            @Override
            public void execute() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        transactions.add(withdraw);

        return new Response(Status.OK, "Withdrawal successful", withdraw);
    }

    private Response handleTransfer(Account[] accounts, double amount) {
        Account source = accounts[0];
        Account destination = accounts[1];
        if (source == null || destination == null) {
            return new Response(Status.BAD_REQUEST, "Both accounts are required for transfer", null);
        }
        if (!source.withdraw(amount)) {
            return new Response(Status.BAD_REQUEST, "Insufficient balance in source account", null);
        }

        destination.deposit(amount);
        TransferTransaction transfer = new TransferTransaction(source, destination, amount);
        transactions.add(transfer);

        return new Response(Status.OK, "Transfer successful", transfer);
    }

    public Response getTransactions() {
        return new Response(Status.OK, "Transactions retrieved successfully", transactions);
    }
}

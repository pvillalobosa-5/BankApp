/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.account.Account;
import core.models.transaction.TransactionType;
import javax.swing.JOptionPane;

/**
 *
 * @author ALVARO PEREZ
 */
public class ExecuteTransactionController {

    public static Response executeTransaction(TransactionController transactionController, TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        try {
            if (amount <= 0) {
                return new Response(Status.BAD_REQUEST, "Amount must be greater than zero.", null);
            }

            if (type == null) {
                return new Response(Status.BAD_REQUEST, "Transaction type is required.", null);
            }

            if (type == TransactionType.TRANSFER && destinationAccount == null) {
                return new Response(Status.BAD_REQUEST, "Destination account is required for transfer.", null);
            }

            if ((type == TransactionType.WITHDRAW || type == TransactionType.TRANSFER) && sourceAccount == null) {
                return new Response(Status.BAD_REQUEST, "Source account is required for this transaction.", null);
            }

            Response response = transactionController.executeTransaction(type, sourceAccount, destinationAccount, amount);
            if (response.getStatus() == Status.OK) {
                return new Response(Status.OK, "Transaction executed successfully!", null);
            } else {
                return new Response(response.getStatus(), response.getMessage(), null);
            }

        } catch (Exception e) {
            return new Response(Status.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage(), null);
        }
    }
}
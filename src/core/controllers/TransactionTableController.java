/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.transaction.Transaction;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TransactionTableController {

    public static Response refreshTransactionTable(DefaultTableModel tableModel, TransactionListController transactionListController) {
        try {
      
            List<Transaction> transactions = (List<Transaction>) transactionListController.getTransactions();

            if (transactions == null || transactions.isEmpty()) {
                return new Response(Status.BAD_REQUEST, "No transactions available", null);
            }

            tableModel.setRowCount(0);

         
            for (Transaction transaction : transactions) {
                tableModel.addRow(new Object[]{
                    transaction.getType().name(),
                    transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "None",
                    transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getId() : "None",
                    transaction.getAmount()
                });
            }

            return new Response(Status.OK, "Transaction table refreshed successfully", null);
        } catch (Exception e) {
            return new Response(Status.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage(), null);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;


import core.controllers.utils.Status;
import core.models.account.Account;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import core.controllers.utils.Response;




public class AccountTableController {

    public static Response refreshAccountTable(DefaultTableModel tableModel, AccountListController accountListController) {
        try {
          
            List<Account> accounts = (List<Account>) accountListController.getAccounts();

            if (accounts == null || accounts.isEmpty()) {
                return new Response(Status.BAD_REQUEST, "No accounts available", null);
            }

            tableModel.setRowCount(0);

            accounts.sort((obj1, obj2) -> obj1.getId().compareTo(obj2.getId()));

            for (Account account : accounts) {
                tableModel.addRow(new Object[]{
                    account.getId(),
                    account.getOwner().getId(),
                    account.getBalance()
                });
            }

            return new Response(Status.OK, "Account table refreshed successfully", null);
        } catch (Exception e) {
            return new Response(Status.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage(), null);
        }
    }
}

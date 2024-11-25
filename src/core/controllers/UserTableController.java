/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.user.User;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserTableController {

    public static Response refreshUserTable(DefaultTableModel tableModel, UserListController userListController) {
        try {
        
            List<User> users = (List<User>) userListController.getUsers();

            if (users == null || users.isEmpty()) {
                return new Response(Status.BAD_REQUEST, "No users available", null);
            }

            tableModel.setRowCount(0);

      
            for (User user : users) {
                tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getName(),
                    user.getEmail()
                });
            }

            return new Response(Status.OK, "User table refreshed successfully", null);
        } catch (Exception e) {
            return new Response(Status.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage(), null);
        }
    }
}

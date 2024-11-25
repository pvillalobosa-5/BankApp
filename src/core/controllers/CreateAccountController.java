/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.user.User;
import javax.swing.JOptionPane;

/**
 *
 * @author ALVARO PEREZ
 */
public class CreateAccountController {

    public static Response createAccount(AccountController accountController, UserController userController, int userId, double initialBalance) {
        try {
            User selectedUser = userController.getUserById(userId);
            if (selectedUser == null) {
                return new Response(Status.BAD_REQUEST, "User not found.", null);
            }
            String accountId = generateUniqueAccountId();

            Response response = accountController.createAccount(accountId, selectedUser, initialBalance);
            if (response.getStatus() == Status.OK) {
                return new Response(Status.OK, "Account created successfully!", null);
            } else {
                return new Response(response.getStatus(), response.getMessage(), null);
            }

        } catch (NumberFormatException e) {
            return new Response(Status.BAD_REQUEST, "Invalid input. Please enter valid numbers.", null);
        } catch (Exception e) {
            return new Response(Status.INTERNAL_SERVER_ERROR, "An unexpected error occurred.", null);
        }
    }

    private static String generateUniqueAccountId() {
        int first = (int) (Math.random() * 1000);
        int second = (int) (Math.random() * 1000000);
        int third = (int) (Math.random() * 100);
        return String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
    }
}
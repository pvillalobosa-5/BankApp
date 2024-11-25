/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALVARO PEREZ
 */
public class UserListController {

    private final List<User> users = new ArrayList<>();

    public Response addUser(User user) {

        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                return new Response(Status.BAD_REQUEST, "User ID already exists", null);
            }
        }
        users.add(user);
        return new Response(Status.OK, "User added successfully", user);
    }

    public Response findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return new Response(Status.OK, "User found", user);
            }
        }
        return new Response(Status.NOT_FOUND, "User not found", null);
    }

    public Response getUsers() {
        return new Response(Status.OK, "Users retrieved successfully", users);
    }
}

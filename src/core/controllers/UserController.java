/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.util.ArrayList;
import core.models.user.User;
import java.util.Random;

/**
 *
 * @author ALVARO PEREZ
 */
public class UserController {

    private static final ArrayList<User> users = new ArrayList<>();

    public static Response registerUser(String firstName, String lastName, int age) {
        if (firstName == null || firstName.isEmpty()) {
            return new Response(Status.BAD_REQUEST, "El nombre no puede estar vacío.", null);
        }
        if (lastName == null || lastName.isEmpty()) {
            return new Response(Status.BAD_REQUEST, "El apellido no puede estar vacío.", null);
        }
        if (age < 18) {
            return new Response(Status.BAD_REQUEST, "El usuario debe tener al menos 18 años.", null);
        }

        int userId = generateRandomID();
        while (isIdDuplicate(userId)) {
            userId = generateRandomID();
        }

        User newUser = new User(userId, firstName, lastName, age);
        users.add(newUser);

        return new Response(Status.OK, "Usuario registrado exitosamente.", newUser);
    }

    private static boolean isIdDuplicate(int id) {
        for (User user : users) {
            if (id == user.getId()) {
                return true;
            }
        }
        return false;
    }

    public static int generateRandomID() {
        Random random = new Random();
        return random.nextInt(1_000_000_000);
    }

    public static Response getUsers() {
        return new Response(Status.OK, "Usuarios recuperados exitosamente.", users);
    }

    public static User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return new User(user.getId(), user.getFirstname(), user.getLastname(), user.getAge());
            }
        }
        return null;
    }
}

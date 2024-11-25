/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.util.ArrayList;
import java.util.List;
import core.models.user.User;
import java.util.Random;

/**
 *
 * @author ALVARO PEREZ
 */
public class UserController {

    private final List<User> users = new ArrayList<>();

    public Response registerUser(String firstName, String lastName, int age) {
        if (firstName == null || firstName.isEmpty()) {
            return new Response(Status.BAD_REQUEST, "El nombre no puede estar vacío.", null);
        }
        if (lastName == null || lastName.isEmpty()) {
            return new Response(Status.BAD_REQUEST, "El apellido no puede estar vacío.", null);
        }
        if (age < 18) {
            return new Response(Status.BAD_REQUEST, "El usuario debe tener al menos 18 años.", null);
        }

        String userId = generateRandomID();
        while (isIdDuplicate(userId)) {
            userId = generateRandomID();
        }
        User newUser = new User(userId, firstName, lastName, age);
        users.add(newUser);
        return new Response(Status.OK, "Usuario registrado exitosamente.", newUser);
    }

    private boolean isIdDuplicate(String id) {
        for (User user : users) {
            if (id.equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    public String generateRandomID() {
        Random random = new Random();

        String part1 = String.format("%03d", random.nextInt(1000)); // 3 dígitos
        String part2 = String.format("%06d", random.nextInt(1000000)); // 6 dígitos
        String part3 = String.format("%02d", random.nextInt(100)); // 2 dígitos

        return part1 + "-" + part2 + "-" + part3;
    }
}

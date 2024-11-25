/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.user;

/**
 *
 * @author edangulo
 */
public class User {

    private final String id;
    private final String firstname;
    private final String lastname;
    private final int age;

    public User(String id, String firstname, String lastname, int age) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;

    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

}

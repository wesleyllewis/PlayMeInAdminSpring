package com.theironyard.entities;


import com.theironyard.utilities.PasswordStorage;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPasswordHash() {return password;}

    public void setPasswordHash(String password) throws PasswordStorage.CannotPerformOperationException{
        this.password = PasswordStorage.createHash(password);
    }

    public boolean isValid(String password) throws Exception{
        return PasswordStorage.verifyPassword(password, getPasswordHash());
    }
}

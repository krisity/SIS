/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class that is used when registering a student and assigning username and password.
 * @author User
 * 
 */
public class User {
    
    // Decalre 2 strings - username and password
    private String username, password;

    // Generated getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
}

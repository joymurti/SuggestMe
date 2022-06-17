/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

/**
 *
 * @author wwijo
 */
public class Admin extends Person {
    final private int role = 1;
    final private int id = 1;
    
    public Admin() {
        setConnection();
    }
    
    public Admin(String username, String password) {
        setUsername(username);
        setPassword(password);
        setConnection();
    }
    
    @Override
    public int getRole() {
        return this.role;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getRoleString() {
        return "Admin";
    }
}

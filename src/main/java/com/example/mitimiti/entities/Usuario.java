
package com.example.mitimiti.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.mitimiti.entities.superclass.Person;

@Entity
@Table(name="usuario")
public class Usuario extends Person{
    private String password;

    public Usuario() {
    }

    public Usuario(String password) {
        this.password = password;
    }

    public Usuario(String password, String id, String mail, String name) {
        super(id, mail, name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "password=" + password + '}';
    }

}

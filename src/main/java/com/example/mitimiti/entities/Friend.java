
package com.example.mitimiti.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.mitimiti.entities.superclass.Person;

@Entity
@Table(name="friend")
public class Friend extends Person {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    public Friend() {
    }

    public Friend(Usuario usuario) {
        this.usuario = usuario;
    }

    public Friend(Usuario usuario, String id, String mail, String name) {
        super(id, mail, name);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Friend{" + "usuario=" + usuario + '}';
    }
 
}

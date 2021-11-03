
package com.example.mitimiti.entity;

import com.example.mitimiti.entity.superclass.Person;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend")
public class Friend extends Person {
    @ManyToOne
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

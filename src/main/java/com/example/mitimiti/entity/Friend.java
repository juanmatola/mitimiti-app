
package com.example.mitimiti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Friend {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private String name;
    private String mail;
    @ManyToOne
    private Usuario usuario;

    public Friend() {
    }

    public Friend(String id, String name, String mail, Usuario usuario) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Friend{" + "id=" + id + ", name=" + name + ", mail=" + mail + ", usuario=" + usuario + '}';
    }
    
    
}

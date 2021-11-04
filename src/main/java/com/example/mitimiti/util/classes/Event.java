
package com.example.mitimiti.util.classes;

import java.util.Date;
import java.util.List;

import com.example.mitimiti.entities.Usuario;

public class Event {
    private Date date;
    private String name;
    private List<Participant> participants;
    private Usuario usuario;
    private Double totalCost;

    public Event() {
    }

    public Event(Date date, String nameEvent, List<Participant> participant, Usuario usuario, Double totalCost) {
        this.date = date;
        this.name = nameEvent;
        this.participants = participant;
        this.usuario = usuario;
        this.totalCost = totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameEvent) {
        this.name = nameEvent;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participant) {
        this.participants = participant;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double TotalCost) {
        this.totalCost = TotalCost;
    }

    @Override
    public String toString() {
        return "Event{" + "date=" + date + ", nameEvent=" + name + ", participant=" + participants + ", usuario=" + usuario + ", TotalCost=" + totalCost + '}';
    }
    
   
    
}

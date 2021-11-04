
package com.example.mitimiti.util.classes;

import java.util.ArrayList;
import java.util.Date;

import com.example.mitimiti.entities.Usuario;

public class Event {
    private Date date;
    private String name;
    private ArrayList<Participant> participants;
    private Usuario usuario;
    private Double totalCost;

    public Event() {
    }

    public Event(Date date, String nameEvent, ArrayList<Participant> participant, Usuario usuario, Double totalCost) {
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

    public String getNameEvent() {
        return name;
    }

    public void setNameEvent(String nameEvent) {
        this.name = nameEvent;
    }

    public ArrayList<Participant> getParticipant() {
        return participants;
    }

    public void setParticipant(ArrayList<Participant> participant) {
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

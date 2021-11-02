package com.example.mitimiti.util.classes;

import java.util.ArrayList;

public class Participant {
    private String nameParticipant;
    private String mail;
    private ArrayList<Spending>spending;
    private Double totalCost;
 
    public Participant() {
    }

    public Participant(String nameParticipant, String mail, ArrayList<Spending> spending, Double totalCost) {
        this.nameParticipant = nameParticipant;
        this.mail = mail;
        this.spending = spending;
        this.totalCost = totalCost;
    }

    public String getNameParticipant() {
        return nameParticipant;
    }

    public void setNameParticipant(String nameParticipant) {
        this.nameParticipant = nameParticipant;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Spending> getSpending() {
        return spending;
    }

    public void setSpending(ArrayList<Spending> spending) {
        this.spending = spending;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Participant{" + "nameParticipant=" + nameParticipant + ", mail=" + mail + ", spending=" + spending + ", totalCost=" + totalCost + '}';
    }

}

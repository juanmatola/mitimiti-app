package com.example.mitimiti.util.classes;

import java.util.ArrayList;

public class Participant {
    private String name;
    private String mail;
    private ArrayList<Expense> expenses;
    private Double totalCost;
 
    public Participant() {
    }

    public Participant(String nameParticipant, String mail, ArrayList<Expense> spending, Double totalCost) {
        this.name = nameParticipant;
        this.mail = mail;
        this.expenses = spending;
        this.totalCost = totalCost;
    }

    public String getNameParticipant() {
        return name;
    }

    public void setNameParticipant(String nameParticipant) {
        this.name = nameParticipant;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Expense> getSpending() {
        return expenses;
    }

    public void setSpending(ArrayList<Expense> spending) {
        this.expenses = spending;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Participant{" + "nameParticipant=" + name + ", mail=" + mail + ", spending=" + expenses + ", totalCost=" + totalCost + '}';
    }

}

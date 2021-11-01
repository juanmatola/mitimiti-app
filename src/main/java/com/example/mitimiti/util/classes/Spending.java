package com.example.mitimiti.util.classes;

import java.util.ArrayList;

public class Spending {

    private ArrayList<Participant> participant;
    private String detail;
    private Double amount;

    public Spending() {
    }

    public Spending(ArrayList<Participant> participant, String detail, Double amount) {
        this.participant = participant;
        this.detail = detail;
        this.amount = amount;
    }

    public ArrayList<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(ArrayList<Participant> participant) {
        this.participant = participant;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Spending{" + "participant=" + participant + ", detail=" + detail + ", amount=" + amount + '}';
    }

}

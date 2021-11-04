package com.example.mitimiti.util.classes;

import java.util.ArrayList;

public class Expense {

    private ArrayList<Participant> participants;
    private String detail;
    private Double amount;

    public Expense() {
    }

    public Expense(ArrayList<Participant> participant, String detail, Double amount) {
        this.participants = participant;
        this.detail = detail;
        this.amount = amount;
    }

    public ArrayList<Participant> getParticipant() {
        return participants;
    }

    public void setParticipant(ArrayList<Participant> participant) {
        this.participants = participant;
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
        return "Spending{" + "participant=" + participants + ", detail=" + detail + ", amount=" + amount + '}';
    }

}

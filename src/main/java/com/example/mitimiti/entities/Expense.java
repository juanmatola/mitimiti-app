package com.example.mitimiti.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Expense {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private String detail;
    private Double amount;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Participant buyer;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Participant> consumers;

    public Expense () {
    	
    }
    
	public Expense(String id, String detail, Double amount, List<Participant> consumers) {
		super();
		this.id = id;
		this.detail = detail;
		this.amount = amount;
		this.consumers = consumers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Participant getBuyer() {
		return buyer;
	}

	public void setBuyer(Participant buyer) {
		this.buyer = buyer;
	}

	public List<Participant> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<Participant> consumers) {
		this.consumers = consumers;
	}
    
}

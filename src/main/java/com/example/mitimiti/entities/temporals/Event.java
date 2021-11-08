
package com.example.mitimiti.entities.temporals;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.mitimiti.entities.Usuario;

@Entity
@Table
public class Event {
	
	
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private Date date;
    private String name;
    @OneToOne
    private Usuario usuario;
    private Double totalCost;
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Participant> participants;

    public Event() {
    	
    }
     
	public Event(String id, Date date, String name, Usuario usuario, Double totalCost,
			List<Participant> participants) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.usuario = usuario;
		this.totalCost = totalCost;
		this.participants = participants;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
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

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
    
}

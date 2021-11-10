package com.example.mitimiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entities.Expense;
import com.example.mitimiti.entities.Participant;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

	public List<Expense> findByBuyer(Participant buyer);

}

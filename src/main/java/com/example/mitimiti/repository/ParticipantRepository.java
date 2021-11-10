package com.example.mitimiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mitimiti.entities.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String>{

}

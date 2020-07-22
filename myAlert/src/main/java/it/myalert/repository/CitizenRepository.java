package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

}

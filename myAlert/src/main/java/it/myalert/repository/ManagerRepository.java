package it.myalert.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	
	
}

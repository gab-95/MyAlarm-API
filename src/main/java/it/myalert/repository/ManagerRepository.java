package it.myalert.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import it.myalert.entity.Manager;
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	
	
}

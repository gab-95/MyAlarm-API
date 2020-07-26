package it.myalert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Alarm;
import it.myalert.entity.Assign;

@Repository
public interface AssignRepository extends JpaRepository<Assign, Integer>{

	public List<Assign> findByAgent_idAgent(int idAgent);
}

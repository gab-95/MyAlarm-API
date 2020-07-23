package it.myalert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Intervention;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Integer>{

	
	public List<Intervention> findByStatusAndType_idType(String status, int idType);
	public List<Intervention> findByStatusOrderByStartDate(String status);

}

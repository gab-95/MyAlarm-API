package it.myalert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Intervention;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Integer>{

	public List<Intervention> findInterventionByStatusAndType_idType(String status, int idType);

}

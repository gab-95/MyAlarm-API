package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>{

	
	@Query(value= "INSERT INTO agent (idUser_FK, StartDate_tsk, EndDate_task, idManager_FK) " + 
				  "VALUES (:idUser, :#{#Agent.startDateTsk}, :#{#Agent.endDateTask}, :idManager);", nativeQuery = true)
	public Agent addAgent (@Param("Agent")Agent Agent, @Param("idManager")int idManager, @Param("idUser")int idUser);
	
	
	
	

}
package it.myalert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Alarm;
import it.myalert.entity.Assign;

@Repository
public interface AssignRepository extends JpaRepository<Assign, Integer>{

	public List<Assign> findByAgent_idAgent(int idAgent);
	
	@Modifying
	@Query("FROM assign WHERE idAgent=:idAgent ORDER BY :field DESC")
	public List<Assign> getAssignAgentAndOrderByField(@Param("idAgent")int idAgent, @Param("field")String field);
	
	public List<Assign> findByAgent_idAgentOrderByStartValidateDesc(int idAssign);
	
	public List<Assign> findByIntervention_idIntervention(int idIntervention);
}

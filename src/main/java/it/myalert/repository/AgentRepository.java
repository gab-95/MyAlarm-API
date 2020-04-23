package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.myalert.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer>{

	@Query("INSERT INTO my_alert.user ( Name, Surname, Email, BirthDate, Sex, Adress, City,Country)\r\n" + 
			"VALUES ('Dario', 'Rollo', 'dario@gmail.com', '2020-04-22 17:33:10', 'M', 'Casa sua', 'S.Cesario', 'ITA');\r\n" + 
			"INSERT INTO my_alert.manager (idUser_FK, StartDate_task, EndDate_task)\r\n" + 
			"VALUES (last_insert_id(), '2020-04-22 17:33:10', '2020-04-22 17:33:10')")
	public Agent addAgent(Agent agent, int idManager);
}
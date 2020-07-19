package it.myalert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Alarm;
import it.myalert.entity.Intervention;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

	public List<Alarm> findByIntervention_idIntervention(int idIntervention);
	public List<Alarm> findByCitizen_idCitizen(int idCitizen);
}

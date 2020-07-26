package it.myalert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Alarm;
import it.myalert.entity.Image;
import it.myalert.entity.Intervention;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	public List<Image> findByIntervention_idIntervention(int idIntervention);
	public List<Image> findByIntervention_idInterventionAndUser_idUser(int idIntervention, int idUser);
	public int deleteByUser_idUser(int idUser);

}

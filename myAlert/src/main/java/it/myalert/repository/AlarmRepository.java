package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.myalert.entity.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

}

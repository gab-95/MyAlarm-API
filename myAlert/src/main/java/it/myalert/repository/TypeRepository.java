package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.myalert.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>{

}

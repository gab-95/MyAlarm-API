package it.myalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.myalert.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
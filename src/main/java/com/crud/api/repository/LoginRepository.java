package com.crud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.api.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	

}

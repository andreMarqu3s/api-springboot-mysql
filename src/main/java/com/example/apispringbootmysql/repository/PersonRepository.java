package com.example.apispringbootmysql.repository;

import com.example.apispringbootmysql.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}

package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.StudentMarkEntity;

public interface StudentMarkRepository extends JpaRepository<StudentMarkEntity, Integer>{

}

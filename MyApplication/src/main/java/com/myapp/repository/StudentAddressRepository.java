package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.StudentAddressEntity;

public interface StudentAddressRepository extends JpaRepository<StudentAddressEntity, Integer>{

}

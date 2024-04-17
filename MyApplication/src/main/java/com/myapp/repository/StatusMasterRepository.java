package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.StatusMasterEntity;

public interface StatusMasterRepository  extends JpaRepository<StatusMasterEntity, Integer> {

}

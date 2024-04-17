package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.entity.StudentEntity;

public interface StudentDetailRepository extends JpaRepository<StudentEntity, Integer>{
	
	@Query(value = "SELECT * FROM student WHERE STUDENT_STATUS='SM_ACTV' AND STUDENT_ID=:studentId",nativeQuery = true)
	StudentEntity getStudentById(@Param("studentId") String studentId);

}

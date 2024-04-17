package com.myapp.service;

import java.util.List;

import com.myapp.dto.SearchStudentInputDTO;
import com.myapp.dto.StudentAddressDTO;
import com.myapp.dto.StudentDetailDto;
import com.myapp.dto.StudentMarkDTO;
import com.myapp.dto.StudentSaveDTO;
import com.myapp.dto.StudentSearchResultDTO;
import com.myapp.entity.StudentEntity;

public interface StudentService {

	List<StudentSaveDTO> getAllStudentDetails();

	Integer creatStudentEntry(StudentSaveDTO studentSaveDTO);

	List<Integer> saveStudentAddressDetails(List<StudentAddressDTO> studentAddressDTOList, String studentId);

	List<Integer> saveStudentMarkDetails(List<StudentMarkDTO> studentMarkDTOList, String studentId);

	List<StudentSearchResultDTO> searchStudent(SearchStudentInputDTO searchStudentInputDTO);
	
	List<StudentDetailDto> searchStudentDetails(SearchStudentInputDTO searchStudentInputDTO);

}

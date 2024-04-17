package com.myapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentSearchResultDTO {
	private Integer studentId;
	private String studentName;
	private String studentFatherName;
	private String studentMotherName;
	List<StudentAddressDTO> studentAddressDTOList = new ArrayList<>();
	List<StudentMarkDTO> studentMarkDTOList = new ArrayList<>();
}

package com.myapp.dto;

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
public class StudentDetailDto {
	private Integer studentId;
	private String studentName;
	private String studentFatherName;
	private String studentMotherName;
	
	private Integer studentAddressid;
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String addressTypeCode;
	private String addressType;
	
	private Integer studentMarkid;
	private String studentMarkStatusCode;
	private String studentMarkStatus;
	private String studentScoredMark;
	private String studentTotalMark;
	private String studentSemDetails;
}

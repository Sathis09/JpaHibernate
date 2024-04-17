package com.myapp.dto;

import java.sql.Date;

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
public class SearchStudentInputDTO {
	private String studentName;
	private String studentFatherName;
	private String studentMotherName;
	private String studentCountry;
	private String studentAddressType;
	private String studentSemDetails;
	private String studentMarkStatus;
	private Date fromDate;
	private Date toDate;
}

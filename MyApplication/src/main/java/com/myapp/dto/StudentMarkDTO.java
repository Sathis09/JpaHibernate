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
public class StudentMarkDTO {
	
	private Integer studentId;
	private Integer studentMarkId;
	private String studentSemDetails;
	private String studentTotalMark;
	private String studentScoredMark;
	private String studentMarkStatus;
	private String studentMarkStatusCode;
	
}

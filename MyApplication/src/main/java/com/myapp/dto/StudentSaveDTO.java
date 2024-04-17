package com.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class StudentSaveDTO {
	private Integer studentId;
	private String studentName;
	private String studentFatherName;
	private String studentMotherName;
	private String studentStatusCode;
	private String studentStatus;
}

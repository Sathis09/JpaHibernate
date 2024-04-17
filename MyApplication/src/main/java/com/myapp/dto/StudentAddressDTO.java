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
public class StudentAddressDTO {
	private Integer studnetId;
	private Integer studnetAddressId;
	private String studentAddress1;
	private String studentAddress2;
	private String studentCountry;
	private String studentAddressTypeCode;
	private String studentAddressType;
}

package com.myapp.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StatusResponse implements Serializable {

	private static final long	serialVersionUID	= -2601345901552818167L;

	private String				status;
	private ErrorMessage		error;
	private Object				data;
	private String				message;

}

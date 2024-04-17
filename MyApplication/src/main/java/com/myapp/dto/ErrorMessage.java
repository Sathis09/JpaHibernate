package com.myapp.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage implements Serializable {

	private static final long	serialVersionUID	= -7043590037049316538L;

	@JsonProperty("errorId")
	private String				errorId;

	@JsonProperty("errorCode")
	private String				errorCode;

	@JsonProperty("errorMessage")
	private String				errorMessage;

}

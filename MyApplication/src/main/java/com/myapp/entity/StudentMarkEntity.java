package com.myapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "student_mark_details")
public class StudentMarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_MARK_ID")
	private Integer studentMarkId;
	
	@Column(name = "STUDENT_ID")
	private String studentId;

	@Column(name = "STU_SEM_DETAILS")
	private String studentSemDetails;
	
	@Column(name = "STU_SCORED_MARK")
	private String studentScoredMark;
	
	@Column(name = "STU_TOTAL_MARK")
	private String studentTotalMark;
	
	@Column(name = "STU_MARK_STATUS")
	private String studentMarkStatus;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATETIME")
	private Date createdDateTime;

}

package com.myapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "student_address")
public class SearchStudentAddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ADDRESS_ID")
	private Integer studentAddressId;
	
	@Column(name = "STUDENT_ID")
	private String studentId;
	
	@Column(name = "STU_ADDRESS_1")
	private String studentAddress1;

	@Column(name = "STU_ADDRESS_2")
	private String studentAddress2;

	@Column(name = "STU_COUNTRY")
	private String studentCountry;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATETIME")
	private Date createdDateTime;

	// @OneToMany(fetch = FetchType.LAZY)
//	@ManyToOne
//	@JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID", insertable = false, updatable = false)
//	private StudentEntity student;
	// name=StudentEntity.STUDENT_ID
	// referencedColumnName=SearchStudentAddressEntity.STUDENT_ID
}

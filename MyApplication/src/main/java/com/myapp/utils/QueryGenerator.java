package com.myapp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.myapp.dto.SearchStudentInputDTO;
import com.myapp.dto.SqlQueryParameterDTO;
import com.myapp.service.impl.StudentServiceImpl;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryGenerator {

	public static SqlQueryParameterDTO studentSearch(SearchStudentInputDTO searchStudentInputDTO) {
		log.info("Entry point of the studentSearch,searchStudentInputDTO={}", searchStudentInputDTO);
		try {
			SqlQueryParameterDTO sqlQueryParameterDTO = new SqlQueryParameterDTO();
			ArrayList<String> conditions = new ArrayList<String>();
			Map<String, Object> parameters = new HashMap<>();
			String sqlQuery = "SELECT s.STUDENT_ID,s.STUDENT_NAME,s.FATHER_NAME,s.MOTHER_NAME, "
					+ "sa.STUDENT_ADDRESS_ID,sa.STU_ADDRESS_1,sa.STU_ADDRESS_2,sa.STU_COUNTRY,sa.STU_ADDRESS_TYPE, "
					+ "sm.STUDENT_MARK_ID,sm.STU_MARK_STATUS,sm.STU_SCORED_MARK,sm.STU_TOTAL_MARK,sm.STU_SEM_DETAILS "
					+ "FROM student s JOIN student_address sa ON sa.STUDENT_ID=s.STUDENT_ID "
					+ "JOIN student_mark_details sm ON sm.STUDENT_ID=s.STUDENT_ID ";

			if (null != searchStudentInputDTO.getStudentName()) {
				conditions.add("s.STUDENT_NAME LIKE :studentName");
				parameters.put("studentName", "%" + searchStudentInputDTO.getStudentName() + "%");
			}

			if (null != searchStudentInputDTO.getStudentFatherName()) {
				conditions.add("s.FATHER_NAME LIKE :fatherName");
				parameters.put("fatherName", "%" + searchStudentInputDTO.getStudentFatherName() + "%");
			}
			if (null != searchStudentInputDTO.getStudentMotherName()) {
				conditions.add("s.MOTHER_NAME LIKE :motherName");
				parameters.put("motherName", searchStudentInputDTO.getStudentMotherName());
			}

			if (null != searchStudentInputDTO.getStudentMarkStatus()) {
				conditions.add("sm.STU_MARK_STATUS=:markStatus");
				parameters.put("markStatus", searchStudentInputDTO.getStudentMarkStatus());
			}

			if (null != searchStudentInputDTO.getStudentSemDetails()) {
				conditions.add("sm.STU_SEM_DETAILS = :semDatails");
				parameters.put("semDatails", searchStudentInputDTO.getStudentSemDetails());
			}
			if (null != searchStudentInputDTO.getStudentAddressType()) {
				conditions.add("sa.STU_ADDRESS_TYPE:addressType");
				parameters.put("addressType", searchStudentInputDTO.getStudentAddressType());
			}
			if (null != searchStudentInputDTO.getStudentCountry()) {
				conditions.add("sa.STU_COUNTRY=:country");
				parameters.put("country", searchStudentInputDTO.getStudentCountry());
			}

			if (null != searchStudentInputDTO.getFromDate() && null != searchStudentInputDTO.getToDate()) {
				conditions.add("sa.STU_COUNTRY=:country");
				parameters.put("country", searchStudentInputDTO.getStudentCountry());
			}

			if (!conditions.isEmpty())
				sqlQuery += "WHERE " + String.join(" AND ", conditions);
			sqlQueryParameterDTO.setParameters(parameters);
			sqlQueryParameterDTO.setSqlQuery(sqlQuery);
			log.info("Exit point #1 of the create Query for search student,sqlQueryParameterDTO={}",
					sqlQueryParameterDTO);
			return sqlQueryParameterDTO;
		} catch (Exception e) {
			log.info("Exit point #2 of the create query for search student");
			log.error("Error while generating a query,ex={}", e);
			return null;
		}

	}
}

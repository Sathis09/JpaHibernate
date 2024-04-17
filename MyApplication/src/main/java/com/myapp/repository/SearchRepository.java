package com.myapp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.dto.StudentAddressDTO;
import com.myapp.dto.StudentDetailDto;
import com.myapp.dto.StudentMarkDTO;
import com.myapp.dto.StudentSearchResultDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SearchRepository {
	private final EntityManager entityManager;

	@Autowired
	public SearchRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<StudentSearchResultDTO> searchStudent(String sqlQuery, Map<String, Object> parameters) {
		log.info("searchStudent---->sqlQuery={},parameters={}", sqlQuery, parameters);

		Query nativeQuery = entityManager.createNativeQuery(sqlQuery);
		for (Map.Entry<String, Object> entry : parameters.entrySet())
			nativeQuery.setParameter(entry.getKey(), entry.getValue());

		List<Object[]> resultList = nativeQuery.getResultList();

		List<StudentSearchResultDTO> studentSearchResultDTOList = new ArrayList<>();

		Map<Integer, StudentSearchResultDTO> dtoMap = new HashMap<>();

		for (Object[] result : resultList) {

			Integer studentId = (Integer) result[0];
			StudentSearchResultDTO studentSearchResultDTO = dtoMap.getOrDefault(studentId,
					new StudentSearchResultDTO());
			studentSearchResultDTO.setStudentId((Integer) result[0]);
			studentSearchResultDTO.setStudentName((String) result[1]);
			studentSearchResultDTO.setStudentFatherName((String) result[2]);
			studentSearchResultDTO.setStudentMotherName((String) result[3]);

			StudentAddressDTO studentAddressDTO = new StudentAddressDTO();
			studentAddressDTO.setStudnetAddressId((Integer) result[4]);
			studentAddressDTO.setStudentAddress1((String) result[5]);
			studentAddressDTO.setStudentAddress2((String) result[6]);
			studentAddressDTO.setStudentCountry((String) result[7]);
			studentAddressDTO.setStudentAddressTypeCode((String) result[8]);
			studentAddressDTO.setStudentAddressType((String) result[8]);

			if (!studentSearchResultDTO.getStudentAddressDTOList().contains(studentAddressDTO)) {
				studentSearchResultDTO.getStudentAddressDTOList().add(studentAddressDTO);
			}

			StudentMarkDTO studentMarkDTO = new StudentMarkDTO();

			studentMarkDTO.setStudentMarkId((Integer) result[9]);
			studentMarkDTO.setStudentMarkStatusCode((String) result[10]);
			studentMarkDTO.setStudentMarkStatus((String) result[10]);
			studentMarkDTO.setStudentScoredMark((String) result[11]);
			studentMarkDTO.setStudentTotalMark((String) result[12]);
			studentMarkDTO.setStudentSemDetails((String) result[13]);

			if (!studentSearchResultDTO.getStudentMarkDTOList().contains(studentMarkDTO)) {
				studentSearchResultDTO.getStudentMarkDTOList().add(studentMarkDTO);
			}
			dtoMap.put(studentId, studentSearchResultDTO);
		}

		return new ArrayList<>(dtoMap.values());
	}

	public List<StudentDetailDto> searchStudentDetails(String sqlQuery, Map<String, Object> parameters) {
		log.info("Entry point of the searchStudentDetails");
		Query nativeQuery = entityManager.createNativeQuery(sqlQuery);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			nativeQuery.setParameter(entry.getKey(), entry.getValue());
		}
		List<Object[]> queryResultList = nativeQuery.getResultList();
		List<StudentDetailDto> studentDetailDtoList = mapQueryResultToDto(queryResultList);

		return studentDetailDtoList;
	}

	private List<StudentDetailDto> mapQueryResultToDto(List<Object[]> queryResultList) {
		List<StudentDetailDto> resultList = new ArrayList<StudentDetailDto>();
		for (Object[] result : queryResultList) {
			StudentDetailDto studentDetailDto = new StudentDetailDto((Integer) result[0], result[1].toString(),result[2].toString(), result[3].toString(),
					(Integer) result[4], result[5].toString(),result[6].toString(), result[7].toString(), result[8].toString(),null,
					(Integer) result[9], result[10].toString(), null, result[11].toString(),result[12].toString(), result[13].toString());
			resultList.add(studentDetailDto);
		}
		return resultList;
	}
}

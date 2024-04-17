package com.myapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.controller.StudentController;
import com.myapp.dto.SearchStudentInputDTO;
import com.myapp.dto.SqlQueryParameterDTO;
import com.myapp.dto.StudentAddressDTO;
import com.myapp.dto.StudentDetailDto;
import com.myapp.dto.StudentMarkDTO;
import com.myapp.dto.StudentSaveDTO;
import com.myapp.dto.StudentSearchResultDTO;
import com.myapp.entity.StudentAddressEntity;
import com.myapp.entity.StudentEntity;
import com.myapp.entity.StudentMarkEntity;
import com.myapp.repository.SearchRepository;
import com.myapp.repository.StudentAddressRepository;
import com.myapp.repository.StudentDetailRepository;
import com.myapp.repository.StudentMarkRepository;
import com.myapp.service.StudentService;
import com.myapp.service.UtilityService;
import com.myapp.utils.ConfigMap;
import com.myapp.utils.ConstantUtil;
import com.myapp.utils.QueryGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDetailRepository studentDetailRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private StudentAddressRepository addressRepository;
	
	@Autowired
	private StudentMarkRepository markRepository;
	
	@Autowired
	private SearchRepository searchRepository;

	@Override
	public List<StudentSaveDTO> getAllStudentDetails() {
		log.info("Entry point of the getAllStudentDetails");
		List<StudentSaveDTO> studentSaveDTOList = new ArrayList<StudentSaveDTO>();
		try {		
			
			if (null == ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_STATUS)
					|| ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_STATUS).isEmpty()
					|| ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_STATUS).size() == 0) {
				utilityService.setStatusMaster();
			}

			List<StudentEntity> studentEntityList = studentDetailRepository.findAll();
			/*studentSaveDTOList = studentEntityList.stream()
					.map(studentEntity ->modelMapper.map(studentEntity, StudentSaveDTO.class))
					.collect(Collectors.toList()); */
			studentSaveDTOList = studentEntityList.stream().map(studentEntity -> {
				StudentSaveDTO studentSaveDTO = new StudentSaveDTO();
				studentSaveDTO.setStudentId(studentEntity.getStudentId());
				studentSaveDTO.setStudentName(studentEntity.getStudentName());
				studentSaveDTO.setStudentFatherName(studentEntity.getFatherName());
				studentSaveDTO.setStudentMotherName(studentEntity.getMotherName());
				studentSaveDTO.setStudentStatusCode(studentEntity.getStudentStatus());
				studentSaveDTO.setStudentStatus(ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_STATUS).get(studentEntity.getStudentStatus()));
				return studentSaveDTO;
			}).collect(Collectors.toList());

			log.info("Exit point #1 of the getAllStudentDetails");
			return studentSaveDTOList;
		} catch (Exception e) {
			log.error("Error while retrieve student details,ex={}",e);
			log.info("Exit point #2 of the getAllStudentDetails");
			return null;
		}

	}

	@Override
	public Integer creatStudentEntry(StudentSaveDTO studentSaveDTO) {
		log.info("Entry point of the creatStudentEntry, studentSaveDTO={}", studentSaveDTO);
		try {
			StudentEntity studentEntity = modelMapper.map(studentSaveDTO, StudentEntity.class);
			studentEntity.setStudentStatus("SM_ACTV");
			studentEntity.setCreatedBy("Admin");
			studentEntity.setCreatedDateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			
			studentDetailRepository.save(studentEntity);
			log.info("Exit point #1 of the creatStudentEntry");
			return studentEntity.getStudentId();
		} catch (Exception e) {
			log.error("Error while save the student Details,ex={}", e);
			log.info("Exit point #2 of the creatStudentEntry");
			return null;
		}

	}

	@Override
	public List<Integer> saveStudentAddressDetails(List<StudentAddressDTO> studentAddressDTOList, String studentId) {
		log.info("Entry point of the saveStudentAddressDetails,studentId={},studentAddressDTOList={}",studentId,studentAddressDTOList);
		try {
			
			List<Integer> list = new ArrayList<Integer>();
			StudentEntity studentEntity = studentDetailRepository.getStudentById(studentId);
			if(null == studentEntity) {
				list.add(0);
			}
			
			List<StudentAddressEntity> studentAddressEntityList = studentAddressDTOList.stream().map(
					studentAddressDTO ->{
						StudentAddressEntity studentAddressEntity = new StudentAddressEntity();
						studentAddressEntity.setStudentId(studentId);
						studentAddressEntity.setStudentAddress1(studentAddressDTO.getStudentAddress1());
						studentAddressEntity.setStudentAddress2(studentAddressDTO.getStudentAddress2());
						studentAddressEntity.setStudentCountry(studentAddressDTO.getStudentCountry());
						studentAddressEntity.setStudentAddressType(studentAddressDTO.getStudentAddressTypeCode());
						studentAddressEntity.setCreatedBy("Admin");
						studentAddressEntity.setCreatedDateTime(new java.sql.Timestamp(System.currentTimeMillis()));
						return studentAddressEntity;
					}).collect(Collectors.toList());
			log.info("studentAddressEntityList={}",studentAddressEntityList);
			
			addressRepository.saveAll(studentAddressEntityList);
			
			studentAddressEntityList.forEach( s -> {
				list.add(s.getStudentAddressId());
			});
			log.info("Exit point #1 of the saveStudentAddressDetails, list={}",list);
			return list;
		}catch (Exception e) {
			log.info("Exit point #2 of the saveStudentAddressDetails");
			log.error("Error while saving the address detils,ex={}",e);
			return null;
		}
	}
	
	@Override
	public List<Integer> saveStudentMarkDetails(List<StudentMarkDTO> studentMarkDTOList, String studentId) {
		log.info("Entry point of the saveStudentMarkDetails,studentId={},studentAddressDTOList={}", studentId,studentMarkDTOList);
		try {

			List<Integer> list = new ArrayList<Integer>();
			StudentEntity studentEntity = studentDetailRepository.getStudentById(studentId);
			if (null == studentEntity) {
				list.add(0);
			}

			List<StudentMarkEntity> studentMarkEntityList = studentMarkDTOList.stream().map(studentMarkDTO -> {
				StudentMarkEntity studentMarkEntity = new StudentMarkEntity();
				studentMarkEntity.setStudentId(studentId);
				studentMarkEntity.setStudentSemDetails(studentMarkDTO.getStudentSemDetails());
				studentMarkEntity.setStudentScoredMark(studentMarkDTO.getStudentScoredMark());
				studentMarkEntity.setStudentTotalMark(studentMarkDTO.getStudentTotalMark());
				studentMarkEntity.setStudentMarkStatus(studentMarkDTO.getStudentMarkStatus());
				studentMarkEntity.setCreatedBy("Admin");
				studentMarkEntity.setCreatedDateTime(new java.sql.Timestamp(System.currentTimeMillis()));
				return studentMarkEntity;
			}).collect(Collectors.toList());
			
			markRepository.saveAll(studentMarkEntityList);
			log.info("--studentMarkEntityList-{}",studentMarkEntityList);
			studentMarkEntityList.forEach(s -> {
				list.add(s.getStudentMarkId());
			});
			log.info("Exit point #1 of the saveStudentMarkDetails, list={}", list);
			return list;
		} catch (Exception e) {
			log.info("Exit point #2 of the saveStudentMarkDetails,ex={}",e);
			log.error("Error while saving the mark detils,ex={}", e);
			return null;
		}
	}

	@Override
	public List<StudentSearchResultDTO> searchStudent(SearchStudentInputDTO searchStudentInputDTO) {
		log.info("Entry point of the searchStudentInputDTO={}",searchStudentInputDTO);
		try {
			List<StudentSearchResultDTO> studentSearchResultDTOList = new ArrayList<StudentSearchResultDTO>();
			SqlQueryParameterDTO sqlQueryParameterDTO = QueryGenerator.studentSearch(searchStudentInputDTO);
			studentSearchResultDTOList = searchRepository.searchStudent(sqlQueryParameterDTO.getSqlQuery(),sqlQueryParameterDTO.getParameters());
			
			log.info("Exit point #1 of the searchStudent, studentSearchResultDTOList size ={}",studentSearchResultDTOList.size());
			return studentSearchResultDTOList;	
		}catch (Exception e) {
			log.info("Error while search student,ex={}",e);
			log.info("Exit point #2 of the searchStudent");
			return null;	
		}
	}
	@Override
	public List<StudentDetailDto> searchStudentDetails(SearchStudentInputDTO searchStudentInputDTO) {
		log.info("Entry ppint of the searchStudentDetails={}",searchStudentInputDTO);
		try {
			List<StudentDetailDto> studentDetailDtoList = new ArrayList<StudentDetailDto>();
			SqlQueryParameterDTO sqlQueryParameterDTO = QueryGenerator.studentSearch(searchStudentInputDTO);
			studentDetailDtoList = searchRepository.searchStudentDetails(sqlQueryParameterDTO.getSqlQuery(),sqlQueryParameterDTO.getParameters());
			
			studentDetailDtoList.forEach(studentDetailDto -> {
					studentDetailDto.setAddressType(ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_ADDRESS_TYPE).get(studentDetailDto.getAddressTypeCode()));
					studentDetailDto.setStudentMarkStatus(ConfigMap.getInstance().getConfigCategoryMap().get(ConstantUtil.MASTER_STATUS).get(studentDetailDto.getStudentMarkStatusCode()));
				});
			
			log.info("Exit point #1 of the searchStudentDetails, StudentDetailDtoList size ={}",studentDetailDtoList.size());
			return studentDetailDtoList;	
		}catch (Exception e) {
			log.info("Error while search student,ex={}",e);
			log.info("Exit point #2 of the searchStudentDetails");
			return null;	
		}
	}

}

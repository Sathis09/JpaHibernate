package com.myapp.controller;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.dto.ErrorMessage;
import com.myapp.dto.SearchStudentInputDTO;
import com.myapp.dto.StatusResponse;
import com.myapp.dto.StudentAddressDTO;
import com.myapp.dto.StudentDetailDto;
import com.myapp.dto.StudentMarkDTO;
import com.myapp.dto.StudentSaveDTO;
import com.myapp.dto.StudentSearchResultDTO;
import com.myapp.service.StudentService;
import com.myapp.utils.ConstantUtil;
import com.myapp.validator.ObjectValidator;

import lombok.extern.slf4j.Slf4j;
import com.myapp.entity.*;
import com.myapp.exception.InvalidInputParameterException;

@RestController
@RequestMapping("/ss")
@Slf4j
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/getall-student")
	public ResponseEntity<StatusResponse> getAllStudentDetails() {
		log.info("Entry point of the getAllStudentDetails");
		try {

			List<StudentSaveDTO> studentEntityList = studentService.getAllStudentDetails();

			if (null == studentEntityList) {
				log.info("Exit point #1 of the getAllStudentDetails");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_GAS_001").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build()).build());
			}
			log.info("Exit point #2 of the getAllStudentDetails");
			return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
					.data(studentEntityList).message("Successfully retrieved the date").build());
		} catch (Exception e) {
			log.error("Error while getAllStudentDetails, ex={}", e);
			log.info("Exit point #3 of the getAllStudentDetails");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR_GAS_002").errorId(UUID.randomUUID().toString())
									.errorMessage("Unknown Error has occured").build()).build());
		}
	}

	/*
	 * @PostMapping("/save") public ResponseEntity<StatusResponse>
	 * saveStudentDetail(@RequestBody StudentSaveDTO studentSaveDTO) {
	 * log.info("Entry point of the saveStudentDetail, studentSaveDTO={}",
	 * studentSaveDTO); try { Integer studentId =
	 * studentService.creatStudentEntry(studentSaveDTO); if (null == studentId) {
	 * log.info("Exit point #1 of the saveStudentDetail,studentSaveDTO={}",
	 * studentSaveDTO); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body(StatusResponse.builder().status(ConstantUtil.FAILURE)
	 * .message("Error while retrieving the data")
	 * .error(ErrorMessage.builder().errorCode("ERR_001").errorId(UUID.randomUUID().
	 * toString()) .errorMessage("Error occure in the service").build()) .build());
	 * }
	 * 
	 * log.info("Exit point #1 of the saveStudentDetail,studentSaveDTO={}",
	 * studentSaveDTO); return
	 * ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(
	 * ConstantUtil.SUCCESS) .data("student id:" +
	 * studentId).message("Successfully retrieved the date").build()); } catch
	 * (Exception e) {
	 * log.info("Exit point #3 of the saveStudentDetail,studentSaveDTO={}",
	 * studentSaveDTO);
	 * log.error("Error while retrieving the saveStudentDetail,ex={}", e); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body(StatusResponse.builder().status(ConstantUtil.FAILURE)
	 * .message("Error while retrieving the data")
	 * .error(ErrorMessage.builder().errorCode("ERR_001").errorId(UUID.randomUUID().
	 * toString()) .errorMessage("Error occure in the service").build()) .build());
	 * }
	 * 
	 * }
	 */

	@PostMapping("/save")
	public ResponseEntity<StatusResponse> saveStudentDetail(@RequestBody Object studentSaveDTOObject)
			throws InvalidInputParameterException {
		log.info("Entry point of the saveStudentDetail, studentSaveDTO={}", studentSaveDTOObject);
		try {

			boolean schemaValidated = ObjectValidator.jsonValidator(studentSaveDTOObject, "StudentDetail.json");
			if (schemaValidated == false) {
				throw new InvalidParameterException("Input paramater doesnot match with the schema defined.");
			}

			StudentSaveDTO studentSaveDTO = modelMapper.map(studentSaveDTOObject, StudentSaveDTO.class);
			log.info("studentSaveDTO={}", studentSaveDTO);
			Integer studentId = studentService.creatStudentEntry(studentSaveDTO);
			if (null == studentId) {
				log.info("Exit point #1 of the saveStudentDetail,studentSaveDTO={}", studentSaveDTO);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_SSD_001").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build())
								.build());
			}

			log.info("Exit point #2 of the saveStudentDetail,studentSaveDTO={}", studentSaveDTO);
			return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
					.data("student id:" + studentId).message("Successfully retrieved the date").build());

		} catch (InvalidParameterException e) {
			log.info("Exit point #3 of the saveStudentDetail,studentSaveDTO={}", studentSaveDTOObject);
			log.error("Error while retrieving the saveStudentDetail,ex={}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Input does't match with schema")
							.error(ErrorMessage.builder().errorCode("ERR_SSD_002").errorId(UUID.randomUUID().toString())
									.errorMessage("Input Not Matched").build())
							.build());

		} catch (Exception e) {
			log.info("Exit point #4 of the saveStudentDetail,studentSaveDTO={}", studentSaveDTOObject);
			log.error("Error while retrieving the saveStudentDetail,ex={}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR_SSD_003").errorId(UUID.randomUUID().toString())
									.errorMessage("Error occure in the service").build())
							.build());
		}

	}

	@PostMapping("/save-address/student/{studentId}")
	public ResponseEntity<StatusResponse> saveStudnetAddress(@RequestBody List<StudentAddressDTO> studentAddressDTOList,
			@PathVariable String studentId) {

		log.info("Entry point of the saveStudnetAddress,studentId={},studentAddressDTOList={}", studentId,
				studentAddressDTOList);
		try {
			List<Integer> list = studentService.saveStudentAddressDetails(studentAddressDTOList, studentId);
			if (null == list) {
				log.info("Exit point #1 of the saveStudnetAddress");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_SSA_001").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build())
								.build());
			} else if (list.isEmpty() || list.contains(0)) {
				log.info("Exit point #2 of the saveStudnetAddress");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_SSA_002").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build())
								.build());
			} else {
				log.info("Exit point #3 of the saveStudnetAddress, studentId={},studentAddressDTOList={}",studentId,studentAddressDTOList);
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
						.data(list).message("Successfully saved the address").build());
			}
		} catch (Exception e) {
			log.error("Error while saving the address detils,ex={}", e);
			log.info("Exit point #4 of the saveStudnetAddress, studentId={},studentAddressDTOList={}",studentId,studentAddressDTOList);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR_SSA_003").errorId(UUID.randomUUID().toString())
									.errorMessage("Error occure in the service").build())
							.build());
		}
	}

	@PostMapping("/save-mark/student/{studentId}")
	public ResponseEntity<StatusResponse> saveStudnetMark(@RequestBody List<StudentMarkDTO> studentMarkDTOList,
			@PathVariable String studentId) {

		log.info("Entry point of the saveStudnetAddress,studentId={},studentAddressDTOList={}", studentId,
				studentMarkDTOList);
		try {
			List<Integer> list = studentService.saveStudentMarkDetails(studentMarkDTOList, studentId);
			log.info("--list->" + list);
			if (null == list) {
				log.info("Exit point #1 of the saveStudnetAddress");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_SSM_001").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build())
								.build());
			} else if (list.isEmpty() || list.contains(0)) {
				log.info("Exit point #2 of the saveStudnetAddress");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
								.message("Error while retrieving the data")
								.error(ErrorMessage.builder().errorCode("ERR_SSM_002").errorId(UUID.randomUUID().toString())
										.errorMessage("Error occure in the service").build())
								.build());
			} else {
				log.info("Exit point #3 of the saveStudnetAddress");
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
						.data(list).message("Successfully saved the address").build());
			}
		} catch (Exception e) {
			log.error("Error while saving the address detils,ex={}", e);
			log.info("Exit point #4 of the saveStudnetAddress");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR_SSM_003").errorId(UUID.randomUUID().toString())
									.errorMessage("Error occure in the service").build())
							.build());
		}
	}

	@PostMapping("/search-student")
	public ResponseEntity<StatusResponse> searchStudent(@RequestBody SearchStudentInputDTO searchStudentInputDTO) {
		log.info("Entry point of the searchStudent, searchStudentInputDTO={}", searchStudentInputDTO);
		try {
			List<StudentSearchResultDTO> studentSearchResultDTOList = studentService
					.searchStudent(searchStudentInputDTO);
			if (null == studentSearchResultDTOList) {
				log.info("Exit point #1 of the searchStudent, searchStudentInputDTO={}",searchStudentInputDTO);
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.FAILURE)
						.data(null).message("Failed to retrieve the data").build());
			} else {
				log.info("Exit point #2 of the searchStudent, searchStudentInputDTO={}",searchStudentInputDTO);
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
						.data(studentSearchResultDTOList).message("Successfully retrieved the data").build());
			}

		} catch (Exception e) {
			log.error("Error while saving the address detils,ex={}", e);
			log.info("Exit point #3 of the searchStudent, searchStudentInputDTO={}",searchStudentInputDTO);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR_SSS_001").errorId(UUID.randomUUID().toString())
									.errorMessage("Error occure in the service").build()).build());
		}
	}

	@PostMapping("/search-student-details")
	public ResponseEntity<StatusResponse> searchStudentDetails(
			@RequestBody SearchStudentInputDTO searchStudentInputDTO) {
		log.info("Entry point of the searchStudentDetails, searchStudentInputDTO={}", searchStudentInputDTO);
		try {
			List<StudentDetailDto> studentSearchResultDTOList = studentService
					.searchStudentDetails(searchStudentInputDTO);
			if (null == studentSearchResultDTOList) {
				log.info("Exit point #1 of the searchStudentDetails, searchStudentInputDTO={}",searchStudentInputDTO);
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.FAILURE)
						.data(null).message("Failed to retrieve the data").build());
			} else {
				log.info("Exit point #2 of the searchStudentDetails, searchStudentInputDTO={}",searchStudentInputDTO);
				return ResponseEntity.status(HttpStatus.OK).body(StatusResponse.builder().status(ConstantUtil.SUCCESS)
						.data(studentSearchResultDTOList).message("Successfully retrieved the data").build());
			}

		} catch (Exception e) {
			log.error("Error while saving the address detils,ex={}", e);
			log.info("Exit point #3 of the searchStudentDetails, searchStudentInputDTO={}",searchStudentInputDTO);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StatusResponse.builder().status(ConstantUtil.FAILURE)
							.message("Error while retrieving the data")
							.error(ErrorMessage.builder().errorCode("ERR").errorId(UUID.randomUUID().toString())
									.errorMessage("Error occure in the service").build())
							.build());
		}
	}
}

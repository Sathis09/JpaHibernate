package com.myapp.validator;
/*
import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.emigrate.gs.exception.StringValueOverflowException;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import lombok.extern.slf4j.Slf4j;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ObjectValidator {
	public static boolean jsonValidator(Object inputObject, String schemaName) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
		
		// store the JSON data in InputStream  
				try (InputStream schemaStream = inputStreamFromClasspath(schemaName)) {

					// read data from the stream and store it into JsonNode  
					JsonNode json = objectMapper.convertValue(inputObject, JsonNode.class);

					// get schema from the schemaStream and store it into JsonSchema  
					JsonSchema schema = schemaFactory.getSchema(schemaStream);

					// create set of validation message and store result in it  
					Set<ValidationMessage> validationResult = schema.validate(json);

					// show the validation errors   
					if (validationResult.isEmpty()) {
						return true;
					} else {
						log.warn("Schema validation failed for schema : {} and input object: {}", schemaName, inputObject);
						validationResult.forEach(vm -> log.warn("Validation fails for input paramaters : " + vm.getMessage()));
						return false;
					}
				} catch (Exception exc) {
					log.error("Exception occured while validating input parameter with schema: ", exc);
					return false;
				}
	}
	
	private static InputStream inputStreamFromClasspath(String schemaName) {

		// returning stream  
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("json-schema/" + schemaName);
	}
}  */

import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.JsonSchemaFactory;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ObjectValidator {

	public static boolean jsonValidator(Object inputObject, String schemaName) {

		// create instance of the ObjectMapper class
		ObjectMapper objectMapper = new ObjectMapper();

		// create an instance of the JsonSchemaFactory using version flag
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

		// store the JSON data in InputStream
		try (InputStream schemaStream = inputStreamFromClasspath(schemaName)) {

			// read data from the stream and store it into JsonNode
			JsonNode json = objectMapper.convertValue(inputObject, JsonNode.class);

			// get schema from the schemaStream and store it into JsonSchema
			JsonSchema schema = schemaFactory.getSchema(schemaStream);

			// create set of validation message and store result in it
			Set<ValidationMessage> validationResult = schema.validate(json);

			// show the validation errors
			if (validationResult.isEmpty()) {
				return true;
			} else {
				log.warn("Schema validation failed for schema : {} and input object: {}", schemaName, inputObject);
				validationResult.forEach(vm -> log.warn("Validation fails for input paramaters : " + vm.getMessage()));
				return false;
			}
		} catch (Exception exc) {
			log.error("Exception occured while validating input parameter with schema: ", exc);
			return false;
		}
	}

	private static InputStream inputStreamFromClasspath(String schemaName) {

		// returning stream
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("json-schema/" + schemaName);
	}
}

package com.serviceDemo.DTO;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class DobCityDTO {
	private String dateOfBirth;
	private Map<String, Integer> dobCityMap = new HashMap<>();

	@Override
	public String toString(){
		return new ObjectMapper().convertValue(this, Map.class).toString();
	}
}

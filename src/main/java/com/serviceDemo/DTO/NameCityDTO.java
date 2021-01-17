package com.serviceDemo.DTO;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class NameCityDTO {
	private String lastName;
	private Map<String, Integer> cityCountMap;
	
	@Override
	public String toString(){
		return new ObjectMapper().convertValue(this, Map.class).toString();
	}

}

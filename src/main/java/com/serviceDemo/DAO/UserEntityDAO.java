package com.serviceDemo.DAO;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private String dateOfBirth;
	
	@Override
	public String toString(){
		return new ObjectMapper().convertValue(this, Map.class).toString();
	}

}

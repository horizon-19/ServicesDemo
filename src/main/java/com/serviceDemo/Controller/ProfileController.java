package com.serviceDemo.Controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceDemo.DTO.DobCityDTO;
import com.serviceDemo.DTO.NameCityDTO;
import com.serviceDemo.DTO.UserEntityDTO;
import com.serviceDemo.Services.UserService;

@RestController
@RequestMapping(value = "/userProfile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/addUser")
	public ResponseEntity<UserEntityDTO> addUserProfile(@RequestBody UserEntityDTO user) throws IllegalArgumentException, ParseException {
		return new ResponseEntity<>(
				new ObjectMapper().convertValue(userService.createNewUser(user), UserEntityDTO.class), HttpStatus.OK);
	}

	@GetMapping(value = "/usersUsinglastname")
	public ResponseEntity<NameCityDTO> fetchUserUsingLastName(@RequestParam(name = "lastName") String lastName) {
		return new ResponseEntity<>(userService.fetchUserUsingLastName(lastName), HttpStatus.OK);

	}
	
	@GetMapping(value = "/usersUsingDOB")
	public ResponseEntity<DobCityDTO> fetchUserUsingDOB(@RequestParam(name = "dateOfBirth") String dateOfBirth) throws ParseException {
		return new ResponseEntity<>(userService.fetchUsersUsingDOB(dateOfBirth), HttpStatus.OK);

	}
	
	@GetMapping(value = "/usersUsingDOBandCity")
	public ResponseEntity<List<UserEntityDTO>> fetchUserUsingDOBandCity(@RequestParam(name = "dateOfBirth") String dateOfBirth, @RequestParam(name = "city") String city) throws ParseException {
		ObjectMapper objMapper = new ObjectMapper();
		List<UserEntityDTO> userEntityDTOs =userService.fetchUsersUsingDOBandCity(dateOfBirth, city).stream().map(e -> objMapper.convertValue(e, UserEntityDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(userEntityDTOs, HttpStatus.OK);

	}
	
	@GetMapping(value = "/usersCommonName")
	public ResponseEntity<Set<String>> fetchUserCommonName() throws ParseException {
		return new ResponseEntity<>(userService.fetchTop10LastNames(), HttpStatus.OK);

	}
	
	
	
	
}

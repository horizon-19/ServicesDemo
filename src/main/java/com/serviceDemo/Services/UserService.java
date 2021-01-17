package com.serviceDemo.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceDemo.DAO.DOBCityCountView;
import com.serviceDemo.DAO.NameCityCountView;
import com.serviceDemo.DAO.UserEntityDAO;
import com.serviceDemo.DTO.DobCityDTO;
import com.serviceDemo.DTO.NameCityDTO;
import com.serviceDemo.DTO.UserEntityDTO;
import com.serviceDemo.Model.UserEntity;
import com.serviceDemo.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserEntityDAO createNewUser(UserEntityDTO userDTO) throws ParseException {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setCity(userDTO.getCity());
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = format.parse(userDTO.getDateOfBirth());
		userEntity.setDateOfBirth(date);
		UserEntityDAO userDAO = new ObjectMapper().convertValue(userRepository.saveAndFlush(userEntity),
				UserEntityDAO.class);
		return userDAO;
	}

	public NameCityDTO fetchUserUsingLastName(String lastName) {
		List<NameCityCountView> nameCityCountList = userRepository.findByLastNameCityCount(lastName);
		Map<String, Integer> cityCountMap = new HashMap<>();
		nameCityCountList.stream().forEach(e -> cityCountMap.put(e.getCity(), e.getCount()));
		NameCityDTO nameCityCount = new NameCityDTO();
		nameCityCount.setLastName(lastName);
		nameCityCount.setCityCountMap(cityCountMap);
		return nameCityCount;
	}

	public DobCityDTO fetchUsersUsingDOB(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dateUtil = format.parse(date);
		List<DOBCityCountView> dobCityCountViewList = userRepository.findByDOBCityCount(dateUtil);
		Map<String, Integer> cityCountMap = new HashMap<>();
		dobCityCountViewList.stream().forEach(e -> cityCountMap.put(e.getCity(), e.getCount()));
		DobCityDTO dobCityDTO = new DobCityDTO();
		dobCityDTO.setDateOfBirth(date);
		dobCityDTO.setDobCityMap(cityCountMap);
		return dobCityDTO;

	}
	
	public List<UserEntity> fetchUsersUsingDOBandCity(String dateOfBirth, String city) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dateUtil = format.parse(dateOfBirth);
		List<UserEntity> userEntityList = userRepository.findByCityAndDateOfBirth(city, dateUtil);
		return userEntityList;
	}

	public Set<String> fetchTop10LastNames(){
		List<String> citySet = userRepository.findDistinctCities().stream().map(u -> u.getCity()).collect(Collectors.toList());
		Set<String> lastNames = new HashSet<>();
		for(String city : citySet){
			lastNames.addAll(userRepository.findTop10ByLastNameEachCity(city, PageRequest.of(0, 10)).stream().map(u -> u.getLastName()).collect(Collectors.toList()));
		}
		return lastNames;
		
	}
}

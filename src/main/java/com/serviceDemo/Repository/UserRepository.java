package com.serviceDemo.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serviceDemo.DAO.DOBCityCountView;
import com.serviceDemo.DAO.NameCityCountView;
import com.serviceDemo.Model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public List<UserEntity> findByLastName(String lastName);
	
	@Query("Select count(u) as count, u.city as city from UserEntity u where u.lastName = :lastName group by u.city")
	public List<NameCityCountView> findByLastNameCityCount(@Param("lastName")String lastName);
	
	@Query("Select count(u) as count, u.city as city from UserEntity u where u.dateOfBirth = :dateOfBirth group by u.city")
	public List<DOBCityCountView> findByDOBCityCount(@Param("dateOfBirth")Date dateOfBirth);
	
	public List<UserEntity> findByCityAndDateOfBirth(String city, Date dateOfBirth);

	@Query("Select u from UserEntity u where u.city = :city order by count(u.lastName) desc")
	public List<UserEntity> findTop10ByLastNameEachCity(@Param("city")String city, Pageable pageable);
	
	@Query("Select distinct u from UserEntity u")
	public List<UserEntity> findDistinctCities();
	
}

package com.serviceDemo.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "firstname", nullable = false, length = 255)
	private String firstName;
	
	@Column(name = "lastname", nullable = false, length = 255)
	private String lastName;
	
	@Column(name = "city", length = 30)
	private String city;
	
	@Column(name = "DOB")
	private Date dateOfBirth;

}

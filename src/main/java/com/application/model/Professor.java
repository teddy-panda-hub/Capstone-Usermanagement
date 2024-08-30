package com.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String professorid;
	private String email;
	private String professorname;
	private String degreecompleted;
	private String department;
	private String experience;
	private String mobile;
	private String gender;
	private String password;
	
}
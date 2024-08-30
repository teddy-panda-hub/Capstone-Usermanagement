package com.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.User;
import com.application.services.ProfessorService;
import com.application.services.UserService;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/userlist")
	@PreAuthorize("hasRole('USER')")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/userprofileDetails/{email}")
	@PreAuthorize("hasRole('USER')")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> getProfileDetails(@PathVariable String email) throws Exception
	{
		List<User> users = userService.fetchProfileByEmail(email);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
	@PreAuthorize("hasRole('USER')")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<User> updateUserProfile(@RequestBody User user) throws Exception
	{
		User userobj = userService.updateUserProfile(user);
		return new ResponseEntity<User>(userobj, HttpStatus.OK);
	}
	
	@GetMapping("/gettotalusers")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		List<Integer> usersCount = new ArrayList<>();
		usersCount.add(users.size());
		return new ResponseEntity<List<Integer>>(usersCount, HttpStatus.OK);
	}
	
}

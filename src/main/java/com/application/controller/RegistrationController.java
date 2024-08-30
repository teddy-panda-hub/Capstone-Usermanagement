package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Professor;
import com.application.model.User;
import com.application.repository.ProfessorRepository;
import com.application.repository.UserRepository;
import com.application.services.ProfessorService;
import com.application.services.UserService;

@RestController
public class RegistrationController 
{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception
	{
        String password=user.getPassword();
		String encodedPassword = passwordEncoder.encode(password);

        // Create a new user object
        User user1 = userService.saveUser(user);
        user1.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user1);
	}
	
	@PostMapping("/registerprofessor")
	@CrossOrigin(origins = "http://localhost:4200")
	public Professor registerProfessor(@RequestBody Professor professor) throws Exception
	{
		String password=professor.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		
		Professor professor2=professorService.saveProfessor(professor);
		professor2.setPassword(encodedPassword);
		
		return professorRepository.save(professor2);
	}
	
	public String getNewID()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) 
        {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
	}
}
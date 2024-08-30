package com.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.model.User;
import com.application.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;
	
	public User saveUser(User user)
	{
		return userRepo.save(user);
	}
	
	public User updateUserProfile(User user)
	{
		return userRepo.save(user);
	}
	
	public List<User> getAllUsers()
	{
		return (List<User>)userRepo.findAll();
	}
	
	public User fetchUserByEmail(String email)
	{
		return userRepo.findByEmail(email);
	}
	
	public User fetchUserByUsername(String username)
	{
		return userRepo.findByUsername(username);
	}
	
	public User fetchUserByEmailAndPassword(String email, String password)
	{
		return userRepo.findByEmailAndPassword(email, password);
	}
	
	public List<User> fetchProfileByEmail(String email)
	{
		return (List<User>)userRepo.findProfileByEmail(email);
	}

    public boolean authenticate(String username, String password) {
        User user = userRepo.findByUsername(username);
//        System.out.println("User Present");
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public void registerUser(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
}
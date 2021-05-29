package com.esertopcu.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esertopcu.dao.user.UserRepository;
import com.esertopcu.domain.user.User;
import com.esertopcu.exception.UserAlreadyExistException;
import com.esertopcu.service.user.UserService;
import com.esertopcu.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerNewUser(UserDto userDto) {
		
		boolean status = usernameExist(userDto.getUsername());
		if(status) {
			throw new UserAlreadyExistException("There is an account with tahy username: " + userDto.getUsername());
		}
		
		User user = new User();
		user.setFirstName(userDto.getFirtname());
		user.setLastName(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		return userRepository.saveUser(user);
	}
	
	private boolean usernameExist(String username) {
		return findUserByUserName(username) != null;
	}

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

	@Override
	public void changeUserPassword(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		userRepository.updateUser(user);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public User deleteUser(User user) {
		return userRepository.deleteUser(user);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepository.findUserByUserName(username);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllUsers();
	}

	
}

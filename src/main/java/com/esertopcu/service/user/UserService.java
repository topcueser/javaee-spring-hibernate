package com.esertopcu.service.user;

import java.util.List;

import com.esertopcu.domain.user.User;
import com.esertopcu.web.dto.UserDto;

public interface UserService {

	User registerNewUser(UserDto userDto);
	
	boolean checkIfValidOldPassword(User user, String oldPassword);
	
	void changeUserPassword(User user, String password);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	User deleteUser(User user);
	
	User findUserById(Long id);
	
	User findUserByEmail(String email);
	
	User findUserByUserName(String username);
	
	List<User> findAllUsers();
}

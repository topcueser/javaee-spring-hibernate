package com.esertopcu.dao.user;

import java.util.List;

import com.esertopcu.domain.user.User;

public interface UserRepository {

	User saveUser(User user);
	
	User updateUser(User user);
	
	User deleteUser(User user);
	
	User findUserById(Long id);
	
	User findUserByEmail(String email);
	
	User findUserByUserName(String username);
	
	List<User> findAllUsers();
}

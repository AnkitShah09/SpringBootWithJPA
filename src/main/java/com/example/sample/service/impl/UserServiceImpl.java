package com.example.sample.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.sample.entity.User;
import com.example.sample.model.SQLIntegrityConstraintViolationException;
import com.example.sample.model.UserDTO;
import com.example.sample.repository.UserRepo;
import com.example.sample.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		if (!CollectionUtils.isEmpty(users))
			return users.stream().map(user -> objectMapper.convertValue(user, UserDTO.class))
					.collect(Collectors.toList());
		else
			return Collections.emptyList();
	}

	@Override
	public UserDTO getUserByUserName(String userName) {
		User user = userRepo.findByName(userName);
		return user != null ? objectMapper.convertValue(user, UserDTO.class) : null;
	}

	@Override
	public String addUser(UserDTO userDTO) {
		try {
			userRepo.save(objectMapper.convertValue(userDTO, User.class));
			return "Successfully Added";
		} catch (IllegalArgumentException e) {
			return "Error in saving user, Error is " + e.getLocalizedMessage();
		} catch (DataAccessException e) {
			throw new SQLIntegrityConstraintViolationException(
					"User with Username " + userDTO.getName() + " already exists in database.");
		}
	}

}

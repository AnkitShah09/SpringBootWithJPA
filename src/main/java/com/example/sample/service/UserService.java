package com.example.sample.service;

import java.util.List;

import javax.validation.Valid;

import com.example.sample.model.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	UserDTO getUserByUserName(String userName);

	String addUser(@Valid UserDTO userDTO);

}

package com.example.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.model.UserDTO;
import com.example.sample.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userName}")
	public UserDTO getUserByName(@PathVariable String userName) {
		return userService.getUserByUserName(userName);
	}

	@PostMapping
	public String addUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.addUser(userDTO);
	}

}

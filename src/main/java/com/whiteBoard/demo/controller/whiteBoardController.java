package com.whiteBoard.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whiteBoard.demo.model.User;
import com.whiteBoard.demo.service.whiteBoardService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class whiteBoardController {
	private final whiteBoardService service = new whiteBoardService();
	
	@PostMapping
	public void register(User user)
	{
		service.userRegister(user);
	}
	
	@PostMapping("/login")
	public void login(User user)
	{
		service.userLogin(user);
	}
	
	@PostMapping("/logout")
	public void logout(User user)
	{
		service.userLogout(user);	
	}
	
	@GetMapping
	public List<User> findAll(User user)
	{
		return service.findAll();
	}
	
	// exception handling -> throws error
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e)
	{
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
	}
	
	
}

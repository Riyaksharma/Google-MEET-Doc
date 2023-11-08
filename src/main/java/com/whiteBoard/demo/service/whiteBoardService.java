package com.whiteBoard.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.whiteBoard.demo.model.User;

import lombok.experimental.var;


@Service
public class whiteBoardService {
	
	private static final List<User> USERS_LIST = new ArrayList<>();
	
	public void userRegister(User user)
	{
		user.setStatus("online");
		USERS_LIST.add(user);
	}
	
	public User userLogin(User user)
	{
		var index = IntStream.range(0, USERS_LIST.size())
				.filter(i -> USERS_LIST.get(i).getEmail().equals(user.getEmail()))
				.findAny()
				.orElseThrow(() -> new RuntimeException ("No user Found"));
		
		var cUser = USERS_LIST.get(index);
		
		if (!cUser.getPassword().equals(user.getPassword()))
		{
			throw new RuntimeErrorException(null, "Password incorrect");
		}
		cUser.setStatus("online");
		return cUser;
	}
	
	public void userLogout(User user)
	{
		var index = IntStream.range(0, USERS_LIST.size())
				.filter(i -> USERS_LIST.get(i).getEmail().equals(user.getEmail()))
				.findAny()
				.orElseThrow(() -> new RuntimeException("No user found"));
	
		var disconnetUser = USERS_LIST.remove(index);
		disconnetUser.setStatus("offline");
			
	}
	
	public List<User> findAll()
	{
		return USERS_LIST;
	}

}

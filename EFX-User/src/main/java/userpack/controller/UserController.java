package userpack.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.HttpMethod;

import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.net.HttpHeaders;

import userpack.service.UserServiceImpl;
import userpack.dto.UserDto;
import userpack.entity.Tasks;
import userpack.entity.User;
import userpack.exception.UserException;

@RestController
@RequestMapping("/app")
public class UserController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//to get all users
	
	@GetMapping("/user/all")
	public ResponseEntity<List<UserDto>> getAll()
	{
		return ResponseEntity.ok(userservice.getAll());
	}
		
	//to view an user
	@GetMapping("/user/uid={id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id)
	{
			
		try 
		{
			return new ResponseEntity(this.userservice.getById(id), HttpStatus.OK);
		}
		catch(UserException var3) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not here");
		}
		
	}
		
		//to create a user
		@PostMapping("/user/new-user")
		public ResponseEntity<UserDto> createNew(@RequestBody UserDto u) 
		{
			
			this.userservice.save(u);
			return new ResponseEntity("Successfully Created", HttpStatus.OK);
		}
		
		//to update a user
		@PutMapping("/task/update/task_id={tid}/user_id={uid}")
		public Object updateTask(@RequestBody Tasks task, @PathVariable Long tid, @PathVariable Long uid)
		{
			UserDto user = this.userservice.getById(uid);
			String s = user.getDesignation();
			String s1="Admin";
			String s2="Manager";
			
			try {
				if(s.equals(s1))
				{
					return restTemplate.postForObject("http://task-service/task/update/task_id="+tid,task,Tasks.class);	
				
				}
				else if(s.equals(s2))
				{
					
					return restTemplate.postForObject("http://task-service/task/update/task_id="+tid,task,Tasks.class);
				}
				else
				{
					return new ResponseEntity("Unsuccessful!! Invalid user",HttpStatus.BAD_REQUEST);
					//return null;
				}
				
			}
			catch(UserException e)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User");
			}
			//return response;
		}
		
		//to delete a user
		@DeleteMapping("/user/access-id={id}/uid={uid}")
		public ResponseEntity<String> deleteUser(@PathVariable Long id, @PathVariable Long uid) 
		{
			UserDto us = this.userservice.getById(id);
			String s = us.getDesignation();
			String s1="Admin";
			if(s.equals(s1))
			{
				this.userservice.deleteById(uid);
				return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity("Unsuccessful!! Invalid user",HttpStatus.BAD_REQUEST);
			}
			
		}
	
		//get task by id
		@GetMapping("/task/task-id={id}")
		public Tasks getTask(@PathVariable Long id)
		{
			
			Tasks response = restTemplate.getForObject("http://task-service/task/tid="+id, Tasks.class);
			return response;
		}
		
		//add new task by any user
		@PostMapping("/task/new-task")
		public Tasks addTask(@RequestBody Tasks task)
		{
			
			Tasks response = restTemplate.postForObject("http://task-service/task/new-task",task, Tasks.class);
			return response;
			
		}
		
		//update a specific task by admin or manager
		@PutMapping("/task/update/task_id={tid}/user_id={uid}")
		public Tasks updateTask(@RequestBody Tasks task, @PathVariable Long tid, @PathVariable Long uid)
		{
			UserDto user = this.userservice.getById(uid);
			String s = user.getDesignation();
			String s1="Admin";
			String s2="Manager";
			if(s.equals(s1))
			{
				
				return restTemplate.postForObject("http://task-service/task/update/task_id="+tid,task,Tasks.class);	
				// restTemplate.put("http://task-service/task/update/task_id="+tid, task);
			}
			else
			{
				return null;
			}
			//return response;
		}
		
		@DeleteMapping("/task/delete/user_id={uid}/task_id={tid}")
		public ResponseEntity<?> deleteTask(@PathVariable Long tid, @PathVariable Long uid)
		{
			UserDto user = this.userservice.getById(uid);
			String s = user.getDesignation();
			String s1="Admin";
			
			try {
			
				if(s.equals(s1))
				{
					
					return restTemplate.getForEntity("http://task-service/task/delete/task_id="+tid, String.class);
					//return restTemplate.de
				}
				else
				{
					return new ResponseEntity("Invalid User",HttpStatus.BAD_REQUEST);
				}
				
			}
			catch(UserException e)
			{
				System.out.println("Invalid");
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user or task id");
			}
			
		}
}

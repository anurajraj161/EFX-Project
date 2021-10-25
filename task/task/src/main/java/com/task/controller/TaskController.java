package com.task.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.task.dto.TaskDto;
import com.task.entity.Tasks;
import com.task.exception.CRUDControllerException;
import com.task.service.TaskServiceImpl;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private TaskServiceImpl taskServiceImpl;
	
	@Autowired
	public TaskController(TaskServiceImpl taskServiceImpl)
	{
		this.taskServiceImpl=taskServiceImpl;
	}
	
	
	//Getting a specified task
	@RequestMapping("/tid={id}")
	public TaskDto findById(@PathVariable Long id) {
		
		try {
			
			return  this.taskServiceImpl.getById(id);
			
		}
		catch(CRUDControllerException var3) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not here");
		}
	}
	
	//Adding new task by employee or manager or admin
	
	@RequestMapping("/new-task")
	public TaskDto saveTask(@RequestBody TaskDto taskdto)
	{
		try {
			TaskDto savedTask = taskServiceImpl.saveTask(taskdto);
			return savedTask;
		}
		catch(Exception e)
		{
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something weent wrong");
			
		}
	}

	//updating a task by manager and admin
	@RequestMapping("/update/task_id={tid}")
	public  Tasks updateTask(@RequestBody TaskDto task, @PathVariable Long tid)
	{
		try
		{
			System.out.println("Updating");
			return taskServiceImpl.updateTask(task, tid);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id");
		}
		
	}
	
	//deleting a task by admin
	
	@RequestMapping("/delete/task_id={tid}")
	public ResponseEntity deleteTask(@PathVariable Long tid)
	{
		try
		{
				this.taskServiceImpl.deleteById(tid);
				return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
				//return "Successfully deleted";
		}
		catch(Exception e)
		{
			//CRUDControllerException ce = new CRUDControllerException("404", "Invalid id");
			return new ResponseEntity("Unknown Taskid", HttpStatus.BAD_REQUEST);
			
		}
		
	}
}

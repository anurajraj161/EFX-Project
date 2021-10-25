package com.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.dto.TaskDto;
import com.task.entity.Tasks;
import com.task.exception.CRUDControllerException;
import com.task.repository.TaskRepository;
import com.task.util.TaskUtil;

@Component
public class TaskServiceImpl{
	
	@Autowired
	private TaskRepository taskRepository;
	
	public TaskDto getById(Long id)
	{
		Optional<TaskDto> optionaluser =
				  Optional.ofNullable(taskRepository.findById(id).map(TaskUtil::entitytoDto)
				  .orElse(null)); 
				  return optionaluser.orElseThrow(() -> new CRUDControllerException(+id+"Not Found"));
				
	}
	
	public TaskDto saveTask(TaskDto taskdto)
	{
		TaskDto savedTask = TaskUtil.entitytoDto(taskRepository.save(TaskUtil.dtotoEntity(taskdto)));
		return savedTask;
	}
	
	public Tasks updateTask(TaskDto taskdto, Long id)
	{
//		TaskDto getUser = null;
//		TaskDto updatedUser = null;
//        Optional optional = taskRepository.findById(id);
//        if (optional.isPresent()) {
//        	 getUser.setT_id(getUser.getT_id());
//        	 getUser.setU_id(getUser.getU_id());
//        	 getUser.setT_name(getUser.getT_name());
//        	 updatedUser = taskRepository.saveTask(getUser);
//        	 System.out.println("Updated");
//        }
//		return updatedUser;
		
		return taskRepository.findById(id).map(task -> {
			task.setU_id(taskdto.getU_id());
			task.setT_name(taskdto.getT_name());
			return taskRepository.save(task);
		})
				.orElseThrow( () -> new CRUDControllerException(+id+" Not Found"));
	}

	public void deleteById(Long tid) {
		
		taskRepository.findById(tid).orElseThrow ( () -> new CRUDControllerException(+tid+" Not Found"));
		taskRepository.deleteById(tid);
	}

	}

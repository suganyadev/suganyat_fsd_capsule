package com.fsd.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fsd.taskmanager.entity.Task;
@Component
public interface TaskService {

	public void includeTask(Task task)throws Exception;
	public void modifyTask(Task task)throws Exception;
	public List<Task> getAllTaskDetail() throws Exception;
	public List<Task> removeTaskDetail(int task_id)throws Exception;

}

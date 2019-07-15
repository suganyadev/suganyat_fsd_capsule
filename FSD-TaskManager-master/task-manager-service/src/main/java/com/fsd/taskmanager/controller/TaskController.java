package com.fsd.taskmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fsd.taskmanager.entity.Task;
import com.fsd.taskmanager.service.TaskService;



@CrossOrigin(origins = "*", allowedHeaders="*")
@org.springframework.stereotype.Controller
public class TaskController {

	@Autowired
	TaskService taskService;
	/**
	 * log
	 */
	Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	/** In this method is used to add the task details.
	 * @param task
	 * @return
	 */
	@RequestMapping(value="/addTask", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String addTask(@RequestBody Task task) {
		LOGGER.info("Entering into addTask Method");
		try {
			taskService.includeTask(task);
		}catch(Exception ex) {
			LOGGER.error("Exception occured in add task detail method");
			return "Failure";
		}
		LOGGER.info("Exit into addTask Method");
		return "SUCCESS";
	}

	/** In this method is used to delete the task details based on task id.
	 * @param task_id
	 * @return
	 */
	@RequestMapping(value="/deleteTask/{task_id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Task> deleteTask(@PathVariable int task_id) {
		LOGGER.info("Entering into delete task Method");
		List<Task> deleteTaskList = null;
		try {
			deleteTaskList = taskService.removeTaskDetail(task_id);
		}catch(Exception ex) {
			LOGGER.error("Exception occured in delete task detail method");
		}
		LOGGER.info("Exit into delete Task Method");
		return deleteTaskList;
	}

	/** In this method is used to editTask the task details.
	 * @param task
	 * @param task_id
	 * @return
	 */
	@RequestMapping(value="/editTask/{task_id}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String editTask(@RequestBody Task task, @PathVariable int task_id) {
		LOGGER.info("Entering into editTask Method");
		try {
			taskService.modifyTask(task);
		}catch(Exception ex) {
			LOGGER.error("Exception occured in editTask detail method");
			return "Failure";
		}
		LOGGER.info("Exit into editTask Method");
		return "updated";
	}

	/** In this method is used to viewTask the task details.
	 * @return
	 */
	@RequestMapping(value="/viewTask", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Task> viewtask() {
		LOGGER.info("Entering into viewtask Method");
		List<Task> viewTaskList = null;
		try {
			viewTaskList = taskService.getAllTaskDetail();
		}catch(Exception ex) {
			LOGGER.error("Exception occured in viewtask detail method");
		}
		LOGGER.info("Exit into viewtask Method");
		return viewTaskList;
	}


}

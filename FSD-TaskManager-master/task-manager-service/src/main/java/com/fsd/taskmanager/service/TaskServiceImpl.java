package com.fsd.taskmanager.service;

import java.sql.Types;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fsd.taskmanager.controller.TaskController;
import com.fsd.taskmanager.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 /**
	 * log
	 */
	Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	/**
	 * @param task
	 * @throws Exception
	 */
	@Override
	public void includeTask(Task task) throws Exception{
		LOGGER.info("Entering into includeTask Method");
		Integer parentID = (Integer)jdbcTemplate.queryForObject("select  coalesce(max(parent_id)+1,1) from parent_task", Integer.class);

		String parentQuery = "insert into parent_task(parent_id,parent_task) values("+parentID+",'"+task.getParentTask()+"')";
		jdbcTemplate.execute(parentQuery);  

		String taskQuery = "INSERT INTO task(parent_id,task,start_date,end_date,priority,status) "
				+ " VALUES("+parentID+",'"+task.getTask()+"','"+task.getStartDate()+"','"+task.getEndDate()+"','"+task.getPriority()+"','"+task.getStatus()+"')";
		LOGGER.info("Exit into includeTask Method");
		jdbcTemplate.execute(taskQuery);  
	}

	/**
	 * @param task
	 */
	public void modifyTask(Task task) throws Exception {
		LOGGER.info("Entering into modifyTask Method");
		String updateTaskDetail = "update task set task =?, priority=?, start_date=?, end_date=? where task_id =? ";
		int[] types = {Types.VARCHAR,Types.INTEGER,Types.DATE,Types.DATE,Types.INTEGER};
		Object[] params = {task.getTask(),task.getPriority(),task.getStartDate(),task.getEndDate(),task.getTaskId()};
		jdbcTemplate.update(updateTaskDetail,params,types);

		String updateParentDetail = "update parent_task set parent_task =? where parent_id =? ";
		Object[] parentObj = {task.getParentTask(),task.getParentTaskId()};
		int[] parentObjType = {Types.VARCHAR,Types.INTEGER};
		LOGGER.info("Exit into modifyTask Method");
		jdbcTemplate.update(updateParentDetail,parentObj,parentObjType);
	}

	/**
	 * @return
	 */
	public List<Task> getAllTaskDetail() throws Exception  {
		LOGGER.info("Entering into getAllTaskDetail Method");
		String taskDetail = "select t.task_id,t.task,t.priority,t.start_date,t.end_date, t.status,pt.parent_task,pt.parent_id as parentTaskId from task t left join parent_task pt on t.parent_id=pt.parent_id";
		List<Task> taskList = jdbcTemplate.query(taskDetail, new BeanPropertyRowMapper(Task.class));
		LOGGER.info("Exit into getAllTaskDetail Method");
		return taskList;
	}

	/**
	 * @param task_id
	 * @return
	 */
	public List<Task> removeTaskDetail(int task_id) throws Exception {
		LOGGER.info("Entering into removeTaskDetail Method");
		String updateTaskSQL = "update task set status='inactive' where task_id =? ";
		Object[] params = {task_id};
		int[] type = {Types.INTEGER};
		jdbcTemplate.update(updateTaskSQL,params,type);
		LOGGER.info("Entering into removeTaskDetail Method");
		return getAllTaskDetail();
	}



}

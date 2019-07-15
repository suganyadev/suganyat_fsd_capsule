package com.fsd.taskmanager;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.taskmanager.entity.Task;
import com.fsd.taskmanager.service.TaskServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerApplicationTests {

	@Autowired
	private TaskServiceImpl svcTests;
	
	@Test
	public void viewtask() {
		try {
			svcTests.getAllTaskDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addtask() {
		Task addVO = new Task();	
		addVO.setTaskId(1);
		addVO.setParentTask("t1");
		addVO.setParentTask("T2");
		addVO.setPriority(20);
		addVO.setStartDate(new Date(2019, 1, 1));
		addVO.setEndDate(new Date(2019, 2, 2));
		try {
			svcTests.includeTask(addVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			svcTests.removeTaskDetail(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		Task updateVO = new Task();	
		updateVO.setTaskId(1);
		updateVO.setPriority(30);
		try {
			svcTests.modifyTask(updateVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

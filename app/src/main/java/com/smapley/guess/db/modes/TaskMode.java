package com.smapley.guess.db.modes;

import com.smapley.guess.db.entity.TasUseEntity;
import com.smapley.guess.db.entity.TaskDetailsEntity;
import com.smapley.guess.db.entity.TaskEntity;

import java.util.List;


public class TaskMode {
	private TaskEntity taskEntity;
	
	private List<TaskDetailsEntity> listTaskDetailsEntities;
	private List<TasUseEntity> tasUseEntities;




	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public List<TaskDetailsEntity> getListTaskDetailsEntities() {
		return listTaskDetailsEntities;
	}

	public void setListTaskDetailsEntities(
			List<TaskDetailsEntity> listTaskDetailsEntities) {
		this.listTaskDetailsEntities = listTaskDetailsEntities;
	}

	public List<TasUseEntity> getTasUseEntities() {
		return tasUseEntities;
	}

	public void setTasUseEntities(List<TasUseEntity> tasUseEntities) {
		this.tasUseEntities = tasUseEntities;
	}
}

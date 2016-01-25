package com.smapley.powerwork.mode;

import com.smapley.powerwork.db.entity.DynamicEntity;
import com.smapley.powerwork.db.entity.FileEntity;
import com.smapley.powerwork.db.entity.ProjectEntity;
import com.smapley.powerwork.db.entity.TaskEntity;
import com.smapley.powerwork.db.entity.UserEntity;

/**
 * Created by smapley on 15/12/30.
 */
public class Pro_Item1_Mode implements BaseMode {

    private DynamicEntity dynamicEntity;
    private UserEntity userEntity;
    private ProjectEntity projectEntity;
    private FileEntity fileEntity;
    private TaskEntity taskEntity;



    public DynamicEntity getDynamicEntity() {
        return dynamicEntity;
    }

    public void setDynamicEntity(DynamicEntity dynamicEntity) {
        this.dynamicEntity = dynamicEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @Override
    public int getType() {
        return dynamicEntity.getType();
    }
}

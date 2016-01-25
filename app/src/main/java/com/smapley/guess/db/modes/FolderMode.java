package com.smapley.guess.db.modes;


import com.smapley.guess.db.entity.FileEntity;
import com.smapley.guess.db.entity.FolderEntity;

import java.util.List;

public class FolderMode {

	private FolderEntity folderEntity;
	private List<FileEntity> listFileEntities;
	private List<FolderMode> folderModes;


	public FolderEntity getFolderEntity() {
		return folderEntity;
	}

	public void setFolderEntity(FolderEntity folderEntity) {
		this.folderEntity = folderEntity;
	}

	public List<FileEntity> getListFileEntities() {
		return listFileEntities;
	}

	public void setListFileEntities(List<FileEntity> listFileEntities) {
		this.listFileEntities = listFileEntities;
	}

	public List<FolderMode> getFolderModes() {
		return folderModes;
	}

	public void setFolderModes(List<FolderMode> folderModes) {
		this.folderModes = folderModes;
	}
}

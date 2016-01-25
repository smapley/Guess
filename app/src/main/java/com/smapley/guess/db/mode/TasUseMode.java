package com.smapley.guess.db.mode;


import com.smapley.guess.db.entity.TasUseEntity;
import com.smapley.guess.db.entity.UserEntity;

public class TasUseMode {

	private TasUseEntity tasUseEntity;

	private UserEntity userEntity;

	

	public TasUseEntity getTasUseEntity() {
		return tasUseEntity;
	}

	public void setTasUseEntity(TasUseEntity tasUseEntity) {
		this.tasUseEntity = tasUseEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
}

package com.smapley.guess.db.modes;

import com.smapley.guess.db.entity.UserBaseEntity;
import com.smapley.guess.db.entity.UserEntity;


public class UserMode {
	private UserEntity userEntity;

	private UserBaseEntity userBaseEntity;


	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public UserBaseEntity getUserBaseEntity() {
		return userBaseEntity;
	}

	public void setUserBaseEntity(UserBaseEntity userBaseEntity) {
		this.userBaseEntity = userBaseEntity;
	}
}

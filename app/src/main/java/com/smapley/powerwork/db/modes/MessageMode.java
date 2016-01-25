package com.smapley.powerwork.db.modes;


import com.smapley.powerwork.db.entity.MessageEntity;
import com.smapley.powerwork.db.entity.UserEntity;
import com.smapley.powerwork.mode.BaseMode;

public class MessageMode implements BaseMode{

	private MessageEntity messageEntity;
	private UserEntity userEntity;

	public MessageEntity getMessageEntity() {
		return messageEntity;
	}

	public void setMessageEntity(MessageEntity messageEntity) {
		this.messageEntity = messageEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public int getType() {
		return messageEntity.getType();
	}
}

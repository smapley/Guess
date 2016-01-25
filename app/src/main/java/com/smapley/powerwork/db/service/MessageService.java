package com.smapley.powerwork.db.service;

import com.smapley.powerwork.application.LocalApplication;
import com.smapley.powerwork.db.entity.MessageEntity;
import com.smapley.powerwork.db.entity.UserEntity;
import com.smapley.powerwork.db.modes.MessageMode;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/12/30.
 */
public class MessageService {
    private static DbManager dbUtils= LocalApplication.getInstance().dbUtils;
    public  static void save(MessageMode messageMode){
        if(messageMode!=null){
            if(messageMode.getMessageEntity()!=null)
                try {
                    dbUtils.saveOrUpdate(messageMode.getMessageEntity());
                } catch (DbException e) {
                    e.printStackTrace();
                }
            if(messageMode.getUserEntity()!=null)
                try {
                    dbUtils.saveOrUpdate(messageMode.getUserEntity());
                } catch (DbException e) {
                    e.printStackTrace();
                }
        }
    }

    public static List<MessageMode> findByUseId(int useId){
        List<MessageMode> list=new ArrayList<>();
        List<MessageEntity> messageEntities=null;
        try {
            messageEntities=dbUtils.selector(MessageEntity.class).where("use_id","=",useId).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        if(messageEntities!=null&&!messageEntities.isEmpty())
            for(MessageEntity messageEntity:messageEntities){
                MessageMode messageMode=new MessageMode();
                messageMode.setMessageEntity(messageEntity);
                try {
                    messageMode.setUserEntity(dbUtils.findById(UserEntity.class,messageEntity.getSrc_use_id()));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                list.add(messageMode);
            }


        return list;
    }
}

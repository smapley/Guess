package com.smapley.guess.db.services;

import com.smapley.guess.application.LocalApplication;
import com.smapley.guess.db.modes.UserMode;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * Created by smapley on 15/12/18.
 */
public class UserService {
    private static DbManager dbUtils = LocalApplication.getInstance().dbUtils;

    public static void save(UserMode userMode) {
        if (userMode != null) {
            try {
                if (userMode.getUserBaseEntity() != null)
                    dbUtils.saveOrUpdate(userMode.getUserBaseEntity());
            } catch (DbException e) {
                e.printStackTrace();
            }

            try {
                if (userMode.getUserEntity() != null)
                    dbUtils.saveOrUpdate(userMode.getUserEntity());
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
}

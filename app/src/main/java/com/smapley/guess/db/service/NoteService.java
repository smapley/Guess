package com.smapley.guess.db.service;

import com.smapley.guess.application.LocalApplication;
import com.smapley.guess.db.entity.NoteDetailsEntity;
import com.smapley.guess.db.entity.NoteEntity;
import com.smapley.guess.db.modes.NoteMode;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * Created by smapley on 15/12/18.
 */
public class NoteService {
    private static DbManager dbUtils= LocalApplication.getInstance().dbUtils;

    public static void save(NoteMode noteMode) {
        if (noteMode != null) {
            try {
                if (noteMode.getNoteEntity() != null)
                    dbUtils.saveOrUpdate(noteMode.getNoteEntity());
            } catch (DbException e) {
                e.printStackTrace();
            }
            if (noteMode.getListnoteDetailsEntities() != null && !noteMode.getListnoteDetailsEntities().isEmpty())
                for (NoteDetailsEntity noteDetailsEntity : noteMode.getListnoteDetailsEntities()) {
                    try {
                        dbUtils.saveOrUpdate(noteDetailsEntity);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public NoteMode findById(int notId) {
        NoteMode noteMode = new NoteMode();

        //添加NoteEntity
        try {
            noteMode.setNoteEntity(dbUtils.findById(NoteEntity.class, notId));
        } catch (DbException e) {
            e.printStackTrace();
        }

        //添加NoteDetailsEntity
        try {
            noteMode.setListnoteDetailsEntities(dbUtils.selector(NoteDetailsEntity.class).where("not_id", "=", notId).findAll());
        } catch (DbException e) {
            e.printStackTrace();
        }

        return noteMode;
    }


}

package com.belatrix.kotlintemplate.storage;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.belatrix.kotlintemplate.model.NoteEntity;

/**
 * Created by emedinaa on 2/25/18.
 */

@Database(entities = {NoteEntity.class}, version = 1)
public class NoteDataBase extends RoomDatabase {
    
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}

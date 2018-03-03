package com.belatrix.kotlintemplate.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author Eduardo Medina.
 */
@Entity(tableName = "tb_notes")
public class NoteEntity implements Serializable {

    @PrimaryKey (autoGenerate= true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "desc")
    private String description;

    @ColumnInfo(name = "favourite")
    private boolean favourite; //false

    //@ColumnInfo(name = "path")
    @Ignore
    private String path;

    public NoteEntity() {
    }

    public NoteEntity(@NonNull int id, String name, String description, boolean favourite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.favourite = favourite;
    }

    public NoteEntity(int id, String name, String description, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public NoteEntity(String name, String description, String path) {
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", favourite=" + favourite +
                ", path='" + path + '\'' +
                '}';
    }
}

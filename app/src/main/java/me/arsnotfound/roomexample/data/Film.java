package me.arsnotfound.roomexample.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
public class Film {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "score")
    private float score;

    @ColumnInfo(name = "subject")
    private final String subject;

    public Film(String name, float score, String subject) {
        this.id = 0;
        this.name = name;
        this.score = score;
        this.subject = subject;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }
}


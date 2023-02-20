package me.arsnotfound.roomexample.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import me.arsnotfound.roomexample.executor.Executors;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmDao filmDao();

    private static AppDatabase INSTANCE;

    private final static List<Film> PREPOPULATE_DATA = Arrays.asList(new Film("Люди в чёрном", 4.5f), new Film("Аватар", 4.7f), new Film("Чебурашка", 4.8f));

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }

        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "film-db").allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.ioThread(() -> {
                    getInstance(context).filmDao().addFilms(PREPOPULATE_DATA);
                });
            }
        }).build();
    }
}

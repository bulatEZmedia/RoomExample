package me.arsnotfound.roomexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.arsnotfound.roomexample.data.AppDatabase;
import me.arsnotfound.roomexample.data.Film;
import me.arsnotfound.roomexample.data.FilmDao;
import me.arsnotfound.roomexample.executor.Executors;
import me.arsnotfound.roomexample.ui.FilmRecycleViewAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private AppDatabase database;
    private FilmDao filmDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = AppDatabase.getInstance(getApplicationContext());
        this.filmDao = database.filmDao();

        this.recyclerView = findViewById(R.id.film_rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        FilmRecycleViewAdapter adapter = new FilmRecycleViewAdapter(new ArrayList<>());

        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(adapter);

        Executors.ioThread(() -> {
            List<Film> films = filmDao.getAllFilms();
            runOnUiThread(() -> {
                adapter.setFilms(films);
            });
        });
    }
}
package me.arsnotfound.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import me.arsnotfound.roomexample.data.AppDatabase;
import me.arsnotfound.roomexample.data.FilmDao;
import me.arsnotfound.roomexample.ui.adapter.FilmRecycleViewAdapter;
import me.arsnotfound.roomexample.ui.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private FilmDao filmDao;
    private MainActivityViewModel viewModel;
    private RecyclerView recyclerView;

    private FilmRecycleViewAdapter filmAdapter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = AppDatabase.getInstance(getApplicationContext());
        this.filmDao = database.filmDao();
        this.viewModel = new ViewModelProvider(this, new MainActivityViewModel.Factory(filmDao)).get(MainActivityViewModel.class);

        this.recyclerView = findViewById(R.id.film_rv);
        this.fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilmActivity.class);
            intent.putExtra("film_id", -1);
            startActivity(intent);
        });

        this.filmAdapter = new FilmRecycleViewAdapter(film -> {
            Intent intent = new Intent(this, FilmActivity.class);
            intent.putExtra("film_id", film.getId());
            startActivity(intent);
        });

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.filmAdapter);

        this.viewModel.getFilmsLiveData().observe(this, films -> filmAdapter.setFilms(films));
    }
}
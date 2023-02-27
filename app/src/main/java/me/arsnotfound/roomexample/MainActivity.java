package me.arsnotfound.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.arsnotfound.roomexample.data.AppDatabase;
import me.arsnotfound.roomexample.data.Film;
import me.arsnotfound.roomexample.data.FilmDao;
import me.arsnotfound.roomexample.executor.Executors;
import me.arsnotfound.roomexample.ui.adapter.FilmRecycleViewAdapter;
import me.arsnotfound.roomexample.ui.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private FilmDao filmDao;
    private MainActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private FilmRecycleViewAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = AppDatabase.getInstance(getApplicationContext());
        this.filmDao = database.filmDao();
        this.viewModel = new ViewModelProvider(this, new MainActivityViewModel.MainActivityViewModelFactory(filmDao)).get(MainActivityViewModel.class);

        this.recyclerView = findViewById(R.id.film_rv);

        this.filmAdapter = new FilmRecycleViewAdapter(film -> {
            Log.d("Film Adapter", film.getName());
        });

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.filmAdapter);

        this.viewModel.getFilmsLiveData().observe(this, films -> filmAdapter.setFilms(films));
    }
}
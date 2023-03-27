package me.arsnotfound.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import me.arsnotfound.roomexample.data.AppDatabase;
import me.arsnotfound.roomexample.data.Film;
import me.arsnotfound.roomexample.data.FilmDao;
import me.arsnotfound.roomexample.ui.viewmodel.FilmActivityViewModel;

public class FilmActivity extends AppCompatActivity {

    private int filmID;

    private FilmActivityViewModel viewModel;

    private EditText nameET;

    private EditText subjectET;

    private EditText rateET;

    private Button updateBtn;
    private Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        filmID = intent.getIntExtra("film_id", -1);

        FilmDao filmDao = AppDatabase.getInstance(this).filmDao();

        viewModel = new ViewModelProvider(this, new FilmActivityViewModel.Factory(filmDao)).get(FilmActivityViewModel.class);

        nameET = findViewById(R.id.name_et);
        rateET = findViewById(R.id.rate_et);
        subjectET = findViewById(R.id.subject_et);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        viewModel.getFilmLiveData(filmID).observe(this, film -> {
            if (film != null) {
                nameET.setText(film.getName());
                rateET.setText(String.format(Locale.getDefault(), "%f", film.getScore()));
                subjectET.setText(film.getSubject());
            }
        });

        updateBtn.setOnClickListener(view -> {
            Film newFilm = new Film(
                    nameET.getText().toString(),
                    Float.parseFloat(rateET.getText().toString()),
                    subjectET.getText().toString()
            );
            if (filmID >= 0) {
                newFilm.setId(filmID);
                filmDao.updateFilm(newFilm);
            } else {
//                newFilm.setId(0);
                filmDao.addFilm(newFilm);
            }

            finish();
        });

        deleteBtn.setOnClickListener(view -> {
            if (filmID >= 0) {
                filmDao.deleteFilm(filmID);
            }

            finish();
        });
    }
}
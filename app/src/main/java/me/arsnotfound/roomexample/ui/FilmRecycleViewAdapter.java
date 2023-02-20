package me.arsnotfound.roomexample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import me.arsnotfound.roomexample.R;
import me.arsnotfound.roomexample.data.Film;

public class FilmRecycleViewAdapter extends RecyclerView.Adapter<FilmRecycleViewAdapter.ViewHolder> {
    private List<Film> films;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView filmNameTV;
        private final TextView filmScoreTV;

        private Film film;

        public ViewHolder(View view) {
            super(view);

            filmNameTV = view.findViewById(R.id.film_name_tv);
            filmScoreTV = view.findViewById(R.id.film_score_tv);
        }

        public void setFilm(Film film) {
            this.film = film;
            filmNameTV.setText(film.getName());
            filmScoreTV.setText(String.format(Locale.getDefault(), "%f", film.getScore()));
        }
    }

    public FilmRecycleViewAdapter(List<Film> films) {
        this.films = films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setFilm(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
}

package me.arsnotfound.roomexample.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmDao {
    @Insert
    void addFilm(Film film);

    @Insert
    void addFilms(List<Film> films);

    @Update
    void updateFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Query("DELETE FROM film WHERE id = :id")
    int deleteFilm(int id);

    @Query("SELECT * FROM film")
    LiveData<List<Film>> getAllFilms();

    @Query("SELECT * FROM film WHERE id = :id")
    LiveData<Film> getFilmByID(int id);
}

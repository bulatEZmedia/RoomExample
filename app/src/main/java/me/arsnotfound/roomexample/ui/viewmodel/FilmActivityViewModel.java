package me.arsnotfound.roomexample.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import me.arsnotfound.roomexample.data.Film;
import me.arsnotfound.roomexample.data.FilmDao;

public class FilmActivityViewModel extends ViewModel {
    private LiveData<Film> filmLiveData;

    private FilmDao dao;

    public LiveData<Film> getFilmLiveData(int filmID) {
        filmLiveData = dao.getFilmByID(filmID);
        return filmLiveData;
    }

    public FilmActivityViewModel(FilmDao dao) {
        this.dao = dao;
    }

    public static class Factory implements ViewModelProvider.Factory {
        FilmDao filmDao;

        public Factory(FilmDao dao) {
            this.filmDao = dao;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new FilmActivityViewModel(filmDao);
        }
    }
}

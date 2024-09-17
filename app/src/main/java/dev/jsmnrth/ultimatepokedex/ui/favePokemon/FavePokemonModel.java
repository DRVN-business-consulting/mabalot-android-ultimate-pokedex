package dev.jsmnrth.ultimatepokedex.ui.favePokemon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavePokemonModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavePokemonModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}
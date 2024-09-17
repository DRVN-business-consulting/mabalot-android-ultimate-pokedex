package dev.jsmnrth.ultimatepokedex.ui.pokemonList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokemonListModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PokemonListModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
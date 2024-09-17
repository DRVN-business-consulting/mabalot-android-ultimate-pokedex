package dev.jsmnrth.ultimatepokedex.ui.trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainerModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrainerModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}
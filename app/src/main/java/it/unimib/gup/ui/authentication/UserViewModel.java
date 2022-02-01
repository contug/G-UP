package it.unimib.gup.ui.authentication;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.repository.IUserRepository;
import it.unimib.gup.repository.UserRepository;

/**
 * ViewModel to manage the user authentication.
 */
public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<AuthenticationResponse> mAuthenticationResponseLiveData;
    private final IUserRepository mUserRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.mUserRepository = new UserRepository(application);
    }

    public MutableLiveData<AuthenticationResponse> signInWithEmail(String email, String password) {
        mAuthenticationResponseLiveData = mUserRepository.signInWithEmail(email, password);
        return mAuthenticationResponseLiveData;
    }

    public MutableLiveData<AuthenticationResponse> signUpWithGoogle(Intent intent) {
        mAuthenticationResponseLiveData = mUserRepository.createUserWithGoogle(intent);
        return mAuthenticationResponseLiveData;
    }

    public MutableLiveData<AuthenticationResponse> signUpWithEmail(String name, String surname, String email, String password) {
        mAuthenticationResponseLiveData = mUserRepository.createUserWithEmail(name, surname, email, password);
        return mAuthenticationResponseLiveData;
    }

    public void clear() {
        mAuthenticationResponseLiveData.postValue(null);
    }


}

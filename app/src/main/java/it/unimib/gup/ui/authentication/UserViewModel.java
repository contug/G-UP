package it.unimib.gup.ui.authentication;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.repository.user.IUserRepository;
import it.unimib.gup.repository.user.UserRepository;
import it.unimib.gup.utils.SharedPreferencesProvider;

/**
 * ViewModel to manage the user authentication.
 */
public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<AuthenticationResponse> mAuthenticationResponseLiveData;
    private final IUserRepository mUserRepository;

    private SharedPreferencesProvider sharedPreferencesProvider;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.mUserRepository = new UserRepository(application);
        sharedPreferencesProvider = new SharedPreferencesProvider(application);
    }

    // The association between mAuthLiveData from the Repository and the mAuthLivedData in UserViewModel occurs only ONCE
    // in AuthenticationActivity. So no need to see if it's null in this case
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
        if (mAuthenticationResponseLiveData != null) {
            mAuthenticationResponseLiveData.postValue(null);
        }
    }

    public boolean getAuthWithGoogle() {
        return sharedPreferencesProvider.getAuthWithGoogle();
    }

}

package it.unimib.gup.repository;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import it.unimib.gup.model.AuthenticationResponse;

/**
 * Interface for Repositories that manage the user authentication.
 */
public interface IUserRepository {
    MutableLiveData<AuthenticationResponse> signInWithEmail(String email, String password);
    MutableLiveData<AuthenticationResponse> createUserWithGoogle(Intent data);
    MutableLiveData<AuthenticationResponse> createUserWithEmail(String name, String surname, String email, String password);
}

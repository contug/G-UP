package it.unimib.gup.repository;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.unimib.gup.R;
import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.utils.SharedPreferencesProvider;

/**
 * Repository to authenticate a User using Firebase Authentication.
 */
public class UserRepository implements IUserRepository {

    private static final String TAG = "UserRepository";

    private final FirebaseAuth mAuth;

    private final Application mApplication;
    SharedPreferencesProvider mSharedPreferencesProvider;

    private final MutableLiveData<AuthenticationResponse> mAuthenticationResponseLiveData;

    public UserRepository(Application application) {
        mAuth = FirebaseAuth.getInstance();
        mApplication = application;
        mAuthenticationResponseLiveData = new MutableLiveData<>();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
    }

    @Override
    public MutableLiveData<AuthenticationResponse> signInWithEmail(String email, String password) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty())  {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail: success");
                                authenticationResponse.setSucces(true);
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    mSharedPreferencesProvider.setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                                    mSharedPreferencesProvider.setUserId(user.getUid());
                                }
                            } else {
                                Log.d(TAG, "signInWithEmail: failure", task.getException());
                                authenticationResponse.setSucces(false);
                                if (task.getException() != null) {
                                    authenticationResponse.setMessage(task.getException().getLocalizedMessage());
                                } else {
                                    authenticationResponse.setMessage(mApplication.getString(R.string.authentication_failure));
                                }
                            }
                            mAuthenticationResponseLiveData.postValue(authenticationResponse);
                        }
                    });
        } else {
            Log.d(TAG, "signInWithEmail: no input");
            authenticationResponse.setSucces(false);
            authenticationResponse.setMessage(mApplication.getString(R.string.invalid_auth_input));
            mAuthenticationResponseLiveData.postValue(authenticationResponse);
        }
        return mAuthenticationResponseLiveData;
    }

    @Override
    public MutableLiveData<AuthenticationResponse> createUserWithGoogle(Intent data) {
        return null;
    }

    @Override
    public MutableLiveData<AuthenticationResponse> createUserWithEmail(String name, String surname, String email, String password) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail: success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                authenticationResponse.setSucces(true);
                                if (user != null) {
                                    mSharedPreferencesProvider.setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                                    mSharedPreferencesProvider.setUserId(user.getUid());
                                }
                            } else {
                                Log.d(TAG, "createUserWithEmail: failure", task.getException());
                                authenticationResponse.setSucces(false);
                                if (task.getException() != null) {
                                    authenticationResponse.setMessage(task.getException().getLocalizedMessage());
                                } else {
                                    authenticationResponse.setMessage(mApplication.getString(R.string.authentication_failure));
                                }
                            }
                            mAuthenticationResponseLiveData.postValue(authenticationResponse);
                        }
                    });
        } else {
            Log.d(TAG, "createUserWithEmail: no input");
            authenticationResponse.setSucces(false);
            authenticationResponse.setMessage(mApplication.getString(R.string.invalid_auth_input));
            mAuthenticationResponseLiveData.postValue(authenticationResponse);
        }

        return mAuthenticationResponseLiveData;
    }
}

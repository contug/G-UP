package it.unimib.gup.repository.user;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.unimib.gup.R;
import it.unimib.gup.model.AuthenticationResponse;
import it.unimib.gup.model.User;
import it.unimib.gup.repository.user.IUserRepository;
import it.unimib.gup.utils.Constants;
import it.unimib.gup.utils.SharedPreferencesProvider;

/**
 * Repository to authenticate a User using Firebase Authentication.
 */
public class UserRepository implements IUserRepository {

    private static final String TAG = "UserRepository";

    private final FirebaseAuth mAuth;
    private final DatabaseReference mFirebaseDatabase;

    private final Application mApplication;
    private final SharedPreferencesProvider mSharedPreferencesProvider;

    private final MutableLiveData<AuthenticationResponse> mAuthenticationResponseLiveData;

    public UserRepository(Application application) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL).getReference();
        mApplication = application;
        mAuthenticationResponseLiveData = new MutableLiveData<>();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
    }

    @Override
    public MutableLiveData<AuthenticationResponse> signInWithEmail(String email, String password) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail: success");
                                authenticationResponse.setSuccess(true);
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    mSharedPreferencesProvider.setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                                    mSharedPreferencesProvider.setUserId(user.getUid());
                                    mSharedPreferencesProvider.setAuthWithGoogle(false);
                                }
                            } else {
                                Log.d(TAG, "signInWithEmail: failure", task.getException());
                                authenticationResponse.setSuccess(false);
                                if (task.getException() != null) {
                                    authenticationResponse.setMessage(task.getException().getLocalizedMessage());
                                } else {
                                    authenticationResponse.setMessage(mApplication.getString(R.string.authentication_failure));
                                }
                            }
                            mAuthenticationResponseLiveData.postValue(authenticationResponse);
                        }
                    });
        return mAuthenticationResponseLiveData;
    }

    @Override
    public MutableLiveData<AuthenticationResponse> createUserWithGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());

            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                            if (task.isSuccessful()) {
                                //Sign in success, updateUI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential: success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                authenticationResponse.setSuccess(true);
                                if (user != null) {
                                    User mUser = new User();
                                    mUser.setId(user.getUid());
                                    mUser.setEmail(user.getEmail());
                                    mUser.setFirstName(account.getGivenName());
                                    mUser.setLastName(account.getFamilyName());
                                    // Save user on Firebase Database
                                    mFirebaseDatabase.child(Constants.USER_COLLECTION).child(mUser.getId()).setValue(mUser);

                                    mSharedPreferencesProvider.
                                            setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                                    mSharedPreferencesProvider.setUserId(user.getUid());
                                    mSharedPreferencesProvider.setAuthWithGoogle(true);
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                authenticationResponse.setSuccess(false);
                                if (task.getException() != null) {
                                    authenticationResponse.setMessage(task.getException().getLocalizedMessage());
                                } else {
                                    authenticationResponse.setMessage(mApplication.getString(R.string.authentication_failure));
                                }
                            }
                            mAuthenticationResponseLiveData.postValue(authenticationResponse);
                        }
                    });
        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            Log.w(TAG, "Google sign in failed", e);
        }
        return mAuthenticationResponseLiveData;
    }

    @Override
    public MutableLiveData<AuthenticationResponse> createUserWithEmail(String name, String surname, String email, String password) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail: success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                authenticationResponse.setSuccess(true);
                                if (user != null) {
                                    User mUser = new User();
                                    mUser.setId(user.getUid());
                                    mUser.setEmail(user.getEmail());
                                    mUser.setFirstName(name);
                                    mUser.setLastName(surname);
                                    // Save user on Firebase Database
                                    mFirebaseDatabase.child(Constants.USER_COLLECTION).child(mUser.getId()).setValue(mUser);

                                    mSharedPreferencesProvider.setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                                    mSharedPreferencesProvider.setUserId(user.getUid());
                                    mSharedPreferencesProvider.setAuthWithGoogle(false);
                                }
                            } else {
                                Log.d(TAG, "createUserWithEmail: failure", task.getException());
                                authenticationResponse.setSuccess(false);
                                if (task.getException() != null) {
                                    authenticationResponse.setMessage(task.getException().getLocalizedMessage());
                                } else {
                                    authenticationResponse.setMessage(mApplication.getString(R.string.authentication_failure));
                                }
                            }
                            mAuthenticationResponseLiveData.postValue(authenticationResponse);
                        }
                    });
        return mAuthenticationResponseLiveData;
    }

}

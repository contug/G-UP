package it.unimib.gup.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * It manages the operations with the SharedPreferences API.
 */
public class SharedPreferencesProvider {

    private final Application mApplication;
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesProvider(Application application) {
        this.mApplication = application;
        sharedPreferences = mApplication.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setAuthenticationToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.AUTHENTICATION_TOKEN, token);
        editor.apply();
    }

    public void setUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USER_ID, userId);
        editor.apply();
    }
}

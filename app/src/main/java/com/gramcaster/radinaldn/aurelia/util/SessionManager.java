package com.gramcaster.radinaldn.aurelia.util;

/**
 * Created by radinaldn on 10/02/19.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gramcaster.radinaldn.aurelia.model.User;

import java.util.HashMap;

/**
 * Created by radinaldn on 03/07/18.
 */

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String EMAIL_VERIFED_AT = "email_verified_at";
    public static final String STATUS = "status";
    public static final String AFFILITAE = "affiliate";
    public static final String SALDO = "saldo";
    public static final String HAK_AKSES = "hak_akses";
    public static final String REMEMBER_TOKEN = "remember_token";
    public static final String CREATED_AT = "creaetd_at";
    public static final String UPDATED_AT = "updated_at";

    public Context get_context() {
        return _context;
    }

    // constructor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(User user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putInt(ID, user.getId());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(EMAIL, user.getEmail());

        if (user.getEmailVerifiedAt()==null){
            editor.putString(EMAIL_VERIFED_AT, "");
        } else {
            editor.putString(EMAIL_VERIFED_AT, user.getEmailVerifiedAt());
        }

        editor.putString(STATUS, user.getStatus());
        if (user.getAffiliate()==null){
            editor.putString(AFFILITAE, "");
        } else {
            editor.putString(AFFILITAE, user.getEmailVerifiedAt());
        }

        if (user.getSaldo()==null){
            editor.putString(SALDO, "");
        } else {
            editor.putString(SALDO, user.getEmailVerifiedAt());
        }

        editor.putString(HAK_AKSES, user.getHakAkses());
        editor.putString(REMEMBER_TOKEN, user.getRememberToken());
        editor.putString(CREATED_AT, user.getCreatedAt());
        editor.putString(UPDATED_AT, user.getUpdatedAt());
        editor.apply();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> dosen = new HashMap<>();
        dosen.put(ID, String.valueOf(sharedPreferences.getInt(ID, 0)));
        dosen.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        dosen.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        dosen.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        dosen.put(EMAIL_VERIFED_AT, sharedPreferences.getString(EMAIL_VERIFED_AT, null));
        dosen.put(STATUS, sharedPreferences.getString(STATUS, null));
        dosen.put(AFFILITAE, sharedPreferences.getString(AFFILITAE, null));
        dosen.put(SALDO, sharedPreferences.getString(SALDO, null));
        dosen.put(HAK_AKSES, sharedPreferences.getString(HAK_AKSES, null));
        dosen.put(REMEMBER_TOKEN, sharedPreferences.getString(REMEMBER_TOKEN, null));
        dosen.put(CREATED_AT, sharedPreferences.getString(CREATED_AT, null));
        dosen.put(UPDATED_AT, sharedPreferences.getString(UPDATED_AT, null));

        return dosen;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void logoutUser() {
        editor.clear();
        editor.apply();
    }

}
package com.example.easyship.application;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.example.easyship.R;
import com.example.easyship.data.test.Utilisateurs;
import com.example.easyship.models.Utilisateur;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// La classe Easyship est la classe dans laquelle je répertorie tous les éléments de base
// de l'application (méthodes, variables statiques). Elle s'instancie avec un Context
public class Easyship {
    private ContextWrapper context;

    public static final String TAG = "EasyshipGlobal";
    public static final String EASYSHIP_PREFERENCES = "EASYSHIP_PREFERENCES";
    public static final String EASYSHIP_ACTIVE_USER = "EASYSHIP_ACTIVE_USER";

    public Easyship (ContextWrapper context){
        this.context = context;
    }

    public void initApp(){
        // TODO: Remplacer par l'utilisateur de la BD en ligne
        Utilisateur utilisateurActif = new Utilisateurs().getUtilisateurTest();
        saveObjectToSharedPreferences(EASYSHIP_ACTIVE_USER, utilisateurActif);
    }

    public static String getDateFromCalendar(Calendar c){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(c.getTime());
    }

    public static String getTimeFromCalendar(Calendar c){
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
    }

    // TODO: Méthode pour générer un id
    public static String getNewParcelId() {
        return "TEST";
    }

    public static boolean stringIsNullOrWhitespace(String... values){
        boolean err = false;
        for (String val : values){
            err = err || (val == null || val.trim().length() <= 0);
        }
        return err;
    }

    public static boolean remainingEmptyFields(Context context, EditText... fieldList){
        boolean remainingEmptyFields = false;
        for(EditText field : fieldList){
            if(stringIsNullOrWhitespace(field.getText().toString())){
                field.setError(context.getString(R.string.err_required_field));
                field.requestFocus();
                remainingEmptyFields = true;
            }
        }
        return remainingEmptyFields;
    }

    public Utilisateur getActiveUser(){
        return (Utilisateur) getObjectFromSharedPreferences(Easyship.EASYSHIP_ACTIVE_USER, Utilisateur.class);
    }

    public void saveObjectToSharedPreferences (String key, Object object) {
        Log.d(TAG, "saveObjectToSharedPreferences() method called.");
        SharedPreferences.Editor editor = context.getSharedPreferences(EASYSHIP_PREFERENCES, Context.MODE_PRIVATE).edit();

        Class<?> objectClass = object.getClass();
        if (Integer.class.equals(objectClass)) {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is an Integer");
            editor.putInt(key, Integer.parseInt(object.toString()));
        }
        else if (Boolean.class.equals(objectClass)) {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a Boolean");
            editor.putBoolean(key, Boolean.parseBoolean(object.toString()));
        }
        else if(Float.class.equals(objectClass) || Double.class.equals(objectClass)){
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a Float");
            editor.putFloat(key, Float.parseFloat(object.toString()));
        }
        else if(String.class.equals(objectClass)){
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a String");
            editor.putString(key, object.toString());
        }
        else {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a " + objectClass.toString());
            String objectToJson = new Gson().toJson(object);
            editor.putString(key, objectToJson);
        }
        editor.apply();
    }

    public void deleteKeyValueFromSharedPreferences (String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(EASYSHIP_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

    public Object getObjectFromSharedPreferences (String key, Class objectClass) {
        SharedPreferences prefs = context.getSharedPreferences(EASYSHIP_PREFERENCES, Context.MODE_PRIVATE);

        if (Integer.class.equals(objectClass)) {
            return prefs.getInt(key, Integer.MIN_VALUE);
        }
        else if (Boolean.class.equals(objectClass)) {
            return prefs.getBoolean(key, Boolean.FALSE);
        }
        else if(Float.class.equals(objectClass) || Double.class.equals(objectClass)){
            return prefs.getFloat(key, Float.MIN_VALUE);
        }
        else if(String.class.equals(objectClass)){
            return prefs.getString(key, null);
        }
        else {
            String targetObject = prefs.getString(key, null);
            if(targetObject != null) {
                return new Gson().fromJson(targetObject, objectClass);
            }
        }

        return null;
    }

}

package com.apphoa.tuanh.projectandroid.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.apphoa.tuanh.projectandroid.LoginActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class SessionManagement {
        SharedPreferences pref;
        Editor editor;
        Context _context;
        // Share pref mode
        int PRIVATE_MODE = 0;
        public static final String keyshare_session = "session" ;
        private static final String IS_LOGIN = "IsLoggedIn";
        public static final String keyID_session = "keyID" ; // key cua User
        public static final String UsernameID_session = "UsernameID" ;
        public static final String Fullname_session = "Fullname" ;
        public static final String Cartnumber_session = "Cartnumber" ;
        public SessionManagement(Context context){
            this._context = context;
            pref = _context.getSharedPreferences(keyshare_session,PRIVATE_MODE);
            editor = pref.edit();
        }

        public  void createLoginSession(String keyid,String username, String fullname, String cartnumber)
        {
            editor.putBoolean(IS_LOGIN, true);
            editor.putString(keyID_session,keyid);
            editor.putString(UsernameID_session,username);
            editor.putString(Fullname_session,fullname);
            editor.putString(Cartnumber_session,cartnumber);
            editor.commit();
        }

        public  void updateSession_cartnumber(String cartnumber)
        {
            editor.putString(Cartnumber_session,cartnumber);
            editor.commit();
        }
        public boolean isLoggedIn(){
            return pref.getBoolean(IS_LOGIN, false);
        }

        public  void checkLogin()
        {
            if(!this.isLoggedIn()){
                // user is not logged in redirect him to Login Activity
                Intent i = new Intent(_context, LoginActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                _context.startActivity(i);
            }
        }
        public void logoutUser(){
            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();

            // After logout redirect user to Loing Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
        //lấy userID
        public String getUserID(){
            String keyID = pref.getString(keyID_session,null);
            return keyID;
        }
        public String getUsername(){
            String keyID = pref.getString(UsernameID_session,null);
            return keyID;
        }
        public HashMap<String ,String> getUserDetails2() // chua thu HashMap<String, User> ???
        {
            HashMap<String, String> user = new HashMap<String, String>();
            // user name
            user.put(keyID_session,pref.getString(keyID_session,null));

            // user email id
            user.put(Cartnumber_session, pref.getString(Cartnumber_session, null));
            //user.put(keyID_session, pref.getString(keyID_session,null));

            return user;
        }
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();
            // user name
            user.put(Fullname_session, pref.getString(Fullname_session, null));

            // user email id
            user.put(Cartnumber_session, pref.getString(Cartnumber_session, null));
            //user.put(keyID_session, pref.getString(keyID_session,null));

            return user;
        }
}

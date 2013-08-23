package ru.petrsu.attt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.io.*;

public class Settings {
    public static final String FILENAME = "settings";
    public static final String SOUND = "sound";
    public static Preferences preferences;

    public static boolean isSoundEnabled() {
        preferences = Gdx.app.getPreferences(FILENAME);
        return  preferences.getBoolean(SOUND, true);
    }

    public static void setSoundEnabled(boolean sound) {
       preferences = Gdx.app.getPreferences(FILENAME);
       preferences.putBoolean(SOUND, sound);
       preferences.flush();
    }
}
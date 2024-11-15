package com.badlogic.gdx;

import java.util.Map;

public interface Preferences {

    Preferences putBoolean(String key, boolean val);

    Preferences putInteger(String key, int val);

    Preferences putLong(String key, long val);

    Preferences putFloat(String key, float val);

    Preferences putString(String key, String val);

    Preferences put(Map<String, ?> vals);

    boolean getBoolean(String key);

    int getInteger(String key);

    long getLong(String key);

    float getFloat(String key);

    String getString(String key);

    boolean getBoolean(String key, boolean defValue);

    int getInteger(String key, int defValue);

    long getLong(String key, long defValue);

    float getFloat(String key, float defValue);

    String getString(String key, String defValue);

    Map<String, ?> get();

    boolean contains(String key);

    void clear();

    void remove(String key);

    void flush();

}

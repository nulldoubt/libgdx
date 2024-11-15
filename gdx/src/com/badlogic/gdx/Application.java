package com.badlogic.gdx;

import com.badlogic.gdx.utils.Clipboard;

public interface Application {

    enum ApplicationType {
        Android, Desktop
    }

    int LOG_NONE = 0;
    int LOG_DEBUG = 3;
    int LOG_INFO = 2;
    int LOG_ERROR = 1;

    ApplicationListener getApplicationListener();

    Graphics getGraphics();

    Audio getAudio();

    Input getInput();

    Files getFiles();

    Net getNet();

    void log(String tag, String message);

    void log(String tag, String message, Throwable exception);

    void error(String tag, String message);

    void error(String tag, String message, Throwable exception);

    void debug(String tag, String message);

    void debug(String tag, String message, Throwable exception);

    void setLogLevel(int logLevel);

    int getLogLevel();

    void setApplicationLogger(ApplicationLogger applicationLogger);

    ApplicationLogger getApplicationLogger();

    ApplicationType getType();

    int getVersion();

    long getJavaHeap();

    long getNativeHeap();

    Preferences getPreferences(String name);

    Clipboard getClipboard();

    void postRunnable(Runnable runnable);

    void exit();

    void addLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);

}

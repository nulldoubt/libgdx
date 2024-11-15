package com.badlogic.gdx;

import com.badlogic.gdx.files.FileHandle;

public interface Files {

    enum FileType {

        Classpath,
        Internal,
        External,
        Absolute,
        Local;

    }

    FileHandle getFileHandle(String path, FileType type);

    FileHandle classpath(String path);

    FileHandle internal(String path);

    FileHandle external(String path);

    FileHandle absolute(String path);

    FileHandle local(String path);

    String getExternalStoragePath();

    boolean isExternalStorageAvailable();

    String getLocalStoragePath();

    boolean isLocalStorageAvailable();

}

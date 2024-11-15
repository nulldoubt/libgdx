package com.badlogic.gdx.assets;

import com.badlogic.gdx.files.FileHandle;

public class AssetDescriptor<T> {

    public final String fileName;
    public final Class<T> type;
    public final AssetLoaderParameters<?> params;

    public FileHandle file;

    public AssetDescriptor(String fileName, Class<T> assetType) {
        this(fileName, assetType, null);
    }

    public AssetDescriptor(FileHandle file, Class<T> assetType) {
        this(file, assetType, null);
    }

    public AssetDescriptor(String fileName, Class<T> assetType, AssetLoaderParameters<T> params) {
        this.fileName = fileName;
        this.type = assetType;
        this.params = params;
    }

    public AssetDescriptor(FileHandle file, Class<T> assetType, AssetLoaderParameters<T> params) {
        this.fileName = file.path();
        this.file = file;
        this.type = assetType;
        this.params = params;
    }

    @Override
    public String toString() {
        return fileName + ", " + type.getName();
    }

}

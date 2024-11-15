package com.badlogic.gdx.assets;

public class AssetLoaderParameters<T> {

    public interface LoadedCallback {

        void finishedLoading(AssetManager assetManager, String fileName, Class<?> type);

    }

    public LoadedCallback loadedCallback;

}

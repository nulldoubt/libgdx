package com.badlogic.gdx;

public interface ApplicationListener {

    default void create() {}

    default void resize(int width, int height) {}

    default void render() {}

    default void pause() {}

    default void resume() {}

    default void dispose() {}

}

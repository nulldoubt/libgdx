package com.badlogic.gdx;

public interface InputProcessor {

    default boolean keyDown(int keycode) {
        return false;
    }

    default boolean keyUp(int keycode) {
        return false;
    }

    default boolean keyTyped(char character) {
        return false;
    }

    default boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    default boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    default boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    default boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    default boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    default boolean scrolled(float amountX, float amountY) {
        return false;
    }

}

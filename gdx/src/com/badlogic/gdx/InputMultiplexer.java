package com.badlogic.gdx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class InputMultiplexer implements InputProcessor {

    private final SnapshotArray<InputProcessor> processors = new SnapshotArray<>(4);

    public InputMultiplexer() {}

    public InputMultiplexer(InputProcessor... processors) {
        this.processors.addAll(processors);
    }

    public void add(int index, InputProcessor processor) {
        if (processor == null)
            throw new NullPointerException("processor cannot be null");
        processors.insert(index, processor);
    }

    public void remove(int index) {
        processors.removeIndex(index);
    }

    public void add(InputProcessor processor) {
        if (processor == null)
            throw new NullPointerException("processor cannot be null");
        processors.add(processor);
    }

    public void remove(InputProcessor processor) {
        processors.removeValue(processor, true);
    }

    public int size() {
        return processors.size;
    }

    public void clear() {
        processors.clear();
    }

    public void set(InputProcessor... processors) {
        this.processors.clear();
        this.processors.addAll(processors);
    }

    public void set(Array<InputProcessor> processors) {
        this.processors.clear();
        this.processors.addAll(processors);
    }

    public SnapshotArray<InputProcessor> get() {
        return processors;
    }

    public boolean keyDown(int keycode) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).keyDown(keycode))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).keyUp(keycode))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean keyTyped(char character) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).keyTyped(character))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).touchDown(screenX, screenY, pointer, button))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).touchUp(screenX, screenY, pointer, button))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).touchCancelled(screenX, screenY, pointer, button))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).touchDragged(screenX, screenY, pointer))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).mouseMoved(screenX, screenY))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++)
                if (((InputProcessor) items[i]).scrolled(amountX, amountY))
                    return true;
        } finally {
            processors.end();
        }
        return false;
    }

}

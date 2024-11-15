package com.badlogic.gdx;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.glutils.GLVersion;

public interface Graphics {

    enum GraphicsType {
        AndroidGL, Mock, LWJGL3
    }

    class DisplayMode {

        public final int width;
        public final int height;
        public final int refreshRate;
        public final int bitsPerPixel;

        protected DisplayMode(int width, int height, int refreshRate, int bitsPerPixel) {
            this.width = width;
            this.height = height;
            this.refreshRate = refreshRate;
            this.bitsPerPixel = bitsPerPixel;
        }

        public String toString() {
            return width + "x" + height + ", bpp: " + bitsPerPixel + ", hz: " + refreshRate;
        }

    }

    class Monitor {

        public final int virtualX;
        public final int virtualY;
        public final String name;

        protected Monitor(int virtualX, int virtualY, String name) {
            this.virtualX = virtualX;
            this.virtualY = virtualY;
            this.name = name;
        }

    }

    class BufferFormat {

        public final int r, g, b, a;
        public final int depth, stencil;
        public final int samples;
        public final boolean coverageSampling;

        public BufferFormat(int r, int g, int b, int a, int depth, int stencil, int samples, boolean coverageSampling) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
            this.depth = depth;
            this.stencil = stencil;
            this.samples = samples;
            this.coverageSampling = coverageSampling;
        }

        public String toString() {
            return "r: " + r + ", g: " + g + ", b: " + b + ", a: " + a + ", depth: " + depth + ", stencil: " + stencil
                + ", num samples: " + samples + ", coverage sampling: " + coverageSampling;
        }

    }

    boolean isGL30Available();

    boolean isGL31Available();

    boolean isGL32Available();

    GL20 getGL20();

    GL30 getGL30();

    GL31 getGL31();

    GL32 getGL32();

    void setGL20(GL20 gl20);

    void setGL30(GL30 gl30);

    void setGL31(GL31 gl31);

    void setGL32(GL32 gl32);

    int getWidth();

    int getHeight();

    int getBackBufferWidth();

    int getBackBufferHeight();

    float getBackBufferScale();

    int getSafeInsetLeft();

    int getSafeInsetTop();

    int getSafeInsetBottom();

    int getSafeInsetRight();

    long getFrameId();

    float getDeltaTime();

    int getFramesPerSecond();

    GraphicsType getType();

    GLVersion getGLVersion();

    float getPpiX();

    float getPpiY();

    float getPpcX();

    float getPpcY();

    float getDensity();

    boolean supportsDisplayModeChange();

    Monitor getPrimaryMonitor();

    Monitor getMonitor();

    Monitor[] getMonitors();

    DisplayMode[] getDisplayModes();

    DisplayMode[] getDisplayModes(Monitor monitor);

    DisplayMode getDisplayMode();

    DisplayMode getDisplayMode(Monitor monitor);

    boolean setFullscreenMode(DisplayMode displayMode);

    boolean setWindowedMode(int width, int height);

    void setTitle(String title);

    void setUndecorated(boolean undecorated);

    void setResizable(boolean resizable);

    void setVSync(boolean vsync);

    void setForegroundFPS(int fps);

    BufferFormat getBufferFormat();

    boolean supportsExtension(String extension);

    void setContinuousRendering(boolean isContinuous);

    boolean isContinuousRendering();

    void requestRendering();

    boolean isFullscreen();

    Cursor newCursor(Pixmap pixmap, int xHotspot, int yHotspot);

    void setCursor(Cursor cursor);

    void setSystemCursor(SystemCursor systemCursor);
    
}

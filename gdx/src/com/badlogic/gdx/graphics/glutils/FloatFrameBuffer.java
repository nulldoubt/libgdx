package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class FloatFrameBuffer extends FrameBuffer {

    FloatFrameBuffer() {}

    protected FloatFrameBuffer(GLFrameBufferBuilder<? extends GLFrameBuffer<Texture>> bufferBuilder) {
        super(bufferBuilder);
    }

    public FloatFrameBuffer(int width, int height, boolean hasDepth) {
        FloatFrameBufferBuilder bufferBuilder = new FloatFrameBufferBuilder(width, height);
        bufferBuilder.addFloatAttachment(GL30.GL_RGBA32F, GL30.GL_RGBA, GL30.GL_FLOAT, false);
        if (hasDepth)
            bufferBuilder.addBasicDepthRenderBuffer();
        this.bufferBuilder = bufferBuilder;

        build();
    }

    @Override
    protected Texture createTexture(FrameBufferTextureAttachmentSpec attachmentSpec) {
        FloatTextureData data = new FloatTextureData(bufferBuilder.width, bufferBuilder.height, attachmentSpec.internalFormat,
            attachmentSpec.format, attachmentSpec.type, attachmentSpec.isGpuOnly);
        Texture result = new Texture(data);
        if (Gdx.app.getType() == ApplicationType.Desktop)
            result.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        else
            result.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        result.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
        return result;
    }

}

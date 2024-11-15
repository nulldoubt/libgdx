package com.badlogic.gdx;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Null;

public interface Audio {

    AudioDevice newAudioDevice(int samplingRate, boolean isMono);

    AudioRecorder newAudioRecorder(int samplingRate, boolean isMono);

    Sound newSound(FileHandle fileHandle);

    Music newMusic(FileHandle file);

    boolean switchOutputDevice(@Null String deviceIdentifier);

    String[] getAvailableOutputDevices();

}

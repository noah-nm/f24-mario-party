package utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
  private boolean running = false;
  private String path;
  private int loopCount = 0;
  private Clip clip;

  public Sound(String filePathString) {
    this.path = filePathString;
  }

  public void play() {
    new Thread(() -> {
      try {

        running = true;
        // load audio file
        File audioFile = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        // clip controls the audio
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.loop(loopCount);

        // start playing the audio
        clip.start();
        this.clip = clip;
        running = false;

      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
      }

    }).start();
  }

  /**
   * Checks if the sound is playing
   * @return true if playing
   */
  public boolean isRunning() {
    return this.running;
  }

  /**
   * Stops the sound
   */
  public void stop() {
    this.clip.stop();
  }

  /**
   * Sets the number of loops
   * Use -1 for infinite looping
   * 
   * @param loopCount
   */
  public void setLoopCount(int loopCount) {
    this.loopCount = loopCount;
  }
}

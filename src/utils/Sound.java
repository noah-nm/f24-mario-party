package utils;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Sound {
  private boolean loop = false;
  private String path;
  private MP3Player soundPlayer;

  public Sound(String filePathString) {
    this.path = filePathString;
  }

  public void play() {
    new Thread(() -> {
      try {
        File f = new File(path);

        MP3Player mp3Player = new MP3Player(f);
        this.soundPlayer = mp3Player;
        mp3Player.setRepeat(loop);
        mp3Player.play();
        
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }

    }).start();
  }

  /**
   * Stops the sound
   */
  public void stop() {
    this.soundPlayer.stop();
  }

  /**
   * Sets if the sound player should keep looping
   * 
   * @param looping true to loop
   */
  public void setLooping(boolean looping) {
    this.loop = looping;
  }
}

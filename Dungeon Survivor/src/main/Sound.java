package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL[] soundURL = new URL[5];
    
    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/dice.wav");
        soundURL[1] = getClass().getResource("/sounds/Hit_Hurt.wav");
        soundURL[2] = getClass().getResource("/sounds/move.wav");
        soundURL[3] = getClass().getResource("/sounds/explosion.wav");
    }
    
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e) {

            System.out.println(clip);
            e.printStackTrace();
        }
    }
    
    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.start();
    }
}

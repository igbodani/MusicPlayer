package edu.isu.project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView play, next, previous;

    @FXML
    private Slider  volume;

    @FXML
    ProgressBar progressBar;

    @FXML
    private Label songName;

    private Image pause;

    private Media song;

    private MediaPlayer musicPlayer;

    private File[] musicFiles;

    private int currentSong;

    private File pauseSong;

    private File playSong;

    private Boolean playing;

    Timeline timer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initializing required objects
        File file = new File("src/main/resources/Songs");
        musicFiles = file.listFiles();
        pauseSong = new File("src/main/resources/icons8-pause-100.png");
        playSong = new File("src/main/resources/icons8-play-50.png");
        song = new Media(musicFiles[currentSong].toURI().toString());
        musicPlayer = new MediaPlayer(song);
        playing = false;

        // Changing volume
        volume.valueProperty().addListener((observable, oldValue, newValue) -> musicPlayer.setVolume((Double) newValue));

        play.setOnMouseClicked(event -> {
            if(!playing) {
                playSong();
            }else{
                pauseSong();
            }
        });

        next.setOnMouseClicked(event -> nextSong());
        previous.setOnMouseClicked(event -> previousSong());

    }

    private void playSong() {
        pause = new Image(pauseSong.toURI().toString());
        play.setImage(pause);
        startTimer();
        musicPlayer.play();
        int temp = musicFiles[currentSong].getName().indexOf('.');
        String songNameString = musicFiles[currentSong].getName().substring(0,temp);
        songName.setText(songNameString);
        playing = true;
    }

    private void pauseSong() {
        play.setImage(new Image(playSong.toURI().toString()));
        timer.stop();
        musicPlayer.pause();
        playing = false;
    }

    private void nextSong() {
        if (currentSong < musicFiles.length){
            currentSong++;
            musicPlayer.stop();
            song = new Media(musicFiles[currentSong].toURI().toString());
            musicPlayer = new MediaPlayer(song);
            playSong();
        }
    }

    private void previousSong(){
        if(currentSong > 0){
            currentSong--;
            song = new Media(musicFiles[currentSong].toURI().toString());
            musicPlayer = new MediaPlayer(song);
            musicPlayer.stop();
            playSong();
        }
    }

    private void startTimer(){
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            double currentTime = musicPlayer.getCurrentTime().toSeconds();
            double end = song.getDuration().toSeconds();
            double time = currentTime/end;
            System.out.println(time);
            System.out.println(end);
            System.out.println(currentTime);
            progressBar.setProgress(time);

            if(time == 1){
             //   progressBar.setProgress(0);
                nextSong();
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }


}

package com.example.timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.Instant;

public class HelloController {
    @FXML
    private Label lblTimer;

    @FXML
    private Label lblTimeUp;

    private Timeline timeline;
    private Instant lastUpdateTime;

    public void initialize() {
        lastUpdateTime = Instant.now();
        lblTimeUp.setVisible(false);
        startCountdown();
    }

    public void startCountdown() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.1), event -> {
                    Instant now = Instant.now();
                    double elapsedSeconds = (double) java.time.Duration.between(lastUpdateTime, now).toMillis() / 1000.0;
                    if (elapsedSeconds >= 1.0 && countdownTimeInSeconds > 0) {
                        countdownTimeInSeconds -= (int) elapsedSeconds;
                        updateCountdownLabel();
                        lastUpdateTime = now;
                    }
                    if (countdownTimeInSeconds <= 0) {
                        timeline.stop();
                        lblTimeUp.setVisible(true);

                        // Here u can change the message when time finish
                        lblTimeUp.setText("Time's Up!");
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateCountdownLabel() {
        int minutes = countdownTimeInSeconds / 60;
        int seconds = countdownTimeInSeconds % 60;
        lblTimer.setText(String.format("%02d:%02d", minutes, seconds));
    }

    //Seconds can be change here (Try 120 second to get 2 minutes, 180 seconds...)
    private int countdownTimeInSeconds = 11; // Cambiamos el valor inicial a 2 segundos
}
package com.example.soleclipsado.Controllers;

import com.example.soleclipsado.Models.AlertBox;
import com.example.soleclipsado.Models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Class responsible for game controls
 * @author Dylan-Tobar, Ricardo-Hallado
 * @version 1.0
 *
 */

public class GameController {

    @FXML private HBox    lettersContainer;
    @FXML private Circle  moonCircle;
    @FXML private Label   errorsLabel;
    @FXML private Label   helpCounterLabel;
    @FXML private Label   statusLabel;
    @FXML private Button  buttonHelp;

    private Game game;
    private int  currentIndex = 0;

    /**
     * Initializes the view with the secret word.
     * Dynamically creates a TextField for each letter.
     *
     * @param word The secret word entered on the welcome screen.
     */
    public void setSecretWord(String word) {
        game = new Game(word);
        lettersContainer.getChildren().clear();
        currentIndex = 0;

        for (int i = 0; i < word.length(); i++) {
            TextField text = new TextField();
            text.setPrefWidth(46);
            text.setPrefHeight(46);
            text.setStyle(
                    "-fx-background-color: #2a2a4e;" +
                            "-fx-border-color: #555588;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 6;" +
                            "-fx-background-radius: 6;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-family: 'Verdana Bold';" +
                            "-fx-font-size: 18;" +
                            "-fx-alignment: center;"
            );
            text.setMaxWidth(46);

            final int pos = i;

            text.setOnMouseClicked(mouseEvent -> currentIndex = pos);

            text.setOnKeyPressed(keyEvent -> {
                String keyText = keyEvent.getText();
                if (keyText == null || keyText.isEmpty()) return;

                char l = keyText.charAt(0);

                if (!Character.isLetter(l) && l != 'ñ' && l != 'Ñ') {
                    text.clear();
                    return;
                }

                text.setText(String.valueOf(l).toUpperCase());
                text.setEditable(false);
                text.positionCaret(1);

                boolean correct = game.compareLetter(l, pos);

                if (correct) {
                    text.setStyle(
                            "-fx-background-color: #1a6b1a;" +
                                    "-fx-border-color: #00FF66;" +
                                    "-fx-border-width: 2;" +
                                    "-fx-border-radius: 6;" +
                                    "-fx-background-radius: 6;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-family: 'Verdana Bold';" +
                                    "-fx-font-size: 18;" +
                                    "-fx-alignment: center;"
                    );
                    if (game.winParty()) {
                        statusLabel.setText("🎉 ¡Has ganado! ¡Felicidades!");
                        statusLabel.setStyle("-fx-text-fill: #00FF99; -fx-effect: dropshadow(gaussian,#00ff99,8,0.4,0,0);");
                        buttonHelp.setDisable(true);
                        AlertBox win = new AlertBox();
                        win.showAlertBox("¡Has Ganado!", "Felicidades", "Juego Terminado.");
                    }
                } else {
                    text.setStyle(
                            "-fx-background-color: #6b1a1a;" +
                                    "-fx-border-color: #FF4444;" +
                                    "-fx-border-width: 2;" +
                                    "-fx-border-radius: 6;" +
                                    "-fx-background-radius: 6;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-family: 'Verdana Bold';" +
                                    "-fx-font-size: 18;" +
                                    "-fx-alignment: center;"
                    );
                    updateEclipse();
                    if (game.loseParty()) {
                        statusLabel.setText("💀 ¡Has perdido! Sigue intentando.");
                        statusLabel.setStyle("-fx-text-fill: #FF4444; -fx-effect: dropshadow(gaussian,#ff4444,8,0.4,0,0);");
                        buttonHelp.setDisable(true);
                        AlertBox lose = new AlertBox();
                        lose.showAlertBox("¡Has Perdido!", "Sigue Intentando", "Juego Terminado.");
                    }
                }
                keyEvent.consume();
            });

            lettersContainer.getChildren().add(text);
        }

        updateEclipse();
        updateHelpCounter();
    }

    /**
     * Updates the moon's position (shadow) based on the errors made.
     */
    private void updateEclipse() {
        int errors = game.getCountError();
        double translateX = 160.0 - (errors * 32.0);
        moonCircle.setTranslateX(translateX);
        errorsLabel.setText("Errores: " + errors + " / 5");
    }

    /**
     * Updates the label with the number of remaining hints.
     */
    private void updateHelpCounter() {
        int remaining = game.getAssistance();
        helpCounterLabel.setText("Ayudas disponibles: " + remaining);
    }

    /**
     * Handles the help button event, revealing the correct letter in the active field.
     *@param event The button's action event.
     */
    @FXML
    public void onHandleButtonHelp(ActionEvent event) {
        if (game.getAssistance() <= 0) {
            return;
        }

        TextField field = (TextField) lettersContainer.getChildren().get(currentIndex);
        char helpLetter = game.helpUser(currentIndex);

        if (helpLetter != '_') {
            field.setText(String.valueOf(helpLetter).toUpperCase());
            field.setStyle(
                    "-fx-background-color: #1a4a6b;" +
                            "-fx-border-color: #FFCC00;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 6;" +
                            "-fx-background-radius: 6;" +
                            "-fx-text-fill: #FFCC00;" +
                            "-fx-font-family: 'Verdana Bold';" +
                            "-fx-font-size: 18;" +
                            "-fx-alignment: center;"
            );
            game.compareLetter(helpLetter, currentIndex);
            if (game.winParty()) {
                statusLabel.setText("🎉 ¡Has ganado con ayuda!");
                AlertBox win = new AlertBox();
                win.showAlertBox("¡Has Ganado!", "¡Con ayuda, pero ganaste!", "Juego Terminado.");
            }
        }
        updateHelpCounter();
    }
}
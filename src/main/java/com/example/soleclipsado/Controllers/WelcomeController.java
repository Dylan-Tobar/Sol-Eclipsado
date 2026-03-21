package com.example.soleclipsado.Controllers;

import com.example.soleclipsado.Models.AlertBox;
import com.example.soleclipsado.Views.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private TextField secretWordTextField;

    @FXML
    void onHandleButtonPlay(ActionEvent event) {
        String checkWord = secretWordTextField.getText();

        boolean hasSpaces = false;
        for(int i=0;i<checkWord.length();i++){
            if(checkWord.charAt(i)== ' ') {
                hasSpaces = true;
            }
        }

        if(checkWord.length()<6 || checkWord.length()>12 || hasSpaces==true){
            AlertBox warning = new AlertBox();
            warning.showAlertBox("El juego no inicio.","Error","Error al digitar la palabra secreta.");
        }
        else{
            GameView gameView;
            try {
                gameView = GameView.getInstance();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameController gameController = gameView.getController();
            gameController.setSecretWord(checkWord);
            gameView.show();
            secretWordTextField.getScene().getWindow().hide();

        }
    }
}


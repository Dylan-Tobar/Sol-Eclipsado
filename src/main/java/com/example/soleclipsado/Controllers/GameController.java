package com.example.soleclipsado.Controllers;

import com.example.soleclipsado.Models.AlertBox;
import com.example.soleclipsado.Models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class GameController {

    @FXML
    private HBox lettersContainer;
    private Game game;
    private int currentIndex = 0;

    public void setSecretWord(String word){
        game = new Game(word);
        for(int i=0;i<word.length();i++){
            TextField text = new TextField();
            int p = i;
            text.setOnMouseClicked(mouseEvent -> {
                currentIndex = p;
            });
            text.setOnKeyPressed(keyEvent ->  {
                char l = keyEvent.getText().charAt(0);
                boolean result = game.compareLetter(l,p);
                if(result == true){
                    text.setStyle("-fx-background-color: green;");
                    if(game.winParty()){
                        AlertBox win = new AlertBox();
                        win.showAlertBox("Has Ganado.","Felicidades","Juego Terminado.");
                    }
                }
                else{
                    text.setStyle("-fx-background-color: red;");
                    if(game.loseParty()){
                        AlertBox lose = new AlertBox();
                        lose.showAlertBox("Has Perdido.","Sigue Intentando","Juego Terminado.");
                    }
                }
            });
            lettersContainer.getChildren().add(text);
        }

    }

    @FXML
    public void onHandleButtonHelp(ActionEvent event){
        TextField field = (TextField) lettersContainer.getChildren().get(currentIndex);
        char helpLetter = game.helpUser(currentIndex);
        field.setText(String.valueOf(helpLetter));
    }
}

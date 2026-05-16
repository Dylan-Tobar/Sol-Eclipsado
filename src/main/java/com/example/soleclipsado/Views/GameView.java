package com.example.soleclipsado.Views;

import com.example.soleclipsado.Controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class responsible for displaying the game screen
 * @author Dylan-Tobar, Ricardo-Hallado
 * @version 1.0
 *
 */

public class GameView extends Stage {

    private GameController controller;

    public GameView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/soleclipsado/game-view.fxml")
        );
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        setTitle("Sol Eclipsado");
        setScene(scene);
        setResizable(false);
    }

    public GameController getController() {
        return controller;
    }

    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            GameViewHolder.INSTANCE = new GameView();
        }
        return GameViewHolder.INSTANCE;
    }

    private static class GameViewHolder {
        private static GameView INSTANCE = null;
    }

}

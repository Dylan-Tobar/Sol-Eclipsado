package com.example.soleclipsado.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeView extends Stage {

    public WelcomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/soleclipsado/welcome-view.fxml")
        );
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        setTitle("Sol Eclipsado - Welcome");
        setScene(scene);
        setResizable(false);
    }

    public static WelcomeView getInstance() throws IOException {
        if (WelcomeViewHolder.INSTANCE == null) {
            WelcomeViewHolder.INSTANCE = new WelcomeView();
        }
        return WelcomeViewHolder.INSTANCE;
    }

    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE = null;
    }

}

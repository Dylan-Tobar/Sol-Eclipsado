package com.example.soleclipsado;
import com.example.soleclipsado.Models.AlertBox;
import com.example.soleclipsado.Views.WelcomeView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        WelcomeView welcomeView = WelcomeView.getInstance();
        welcomeView.show();
    }

}


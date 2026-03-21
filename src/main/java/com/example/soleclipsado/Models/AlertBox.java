package com.example.soleclipsado.Models;

import javafx.scene.control.Alert;

public class AlertBox implements IAlertBox{
    /**
     * @param title
     * @param header
     * @param message
     */
    @Override
    public void showAlertBox(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

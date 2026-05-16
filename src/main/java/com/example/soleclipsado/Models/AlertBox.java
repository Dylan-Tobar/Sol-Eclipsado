package com.example.soleclipsado.Models;

import javafx.scene.control.Alert;

/**
 * Class responsible for displaying an alert box
 * @author Dylan-Tobar, Ricardo-Hallado
 * @version 1.0
 *
 */

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

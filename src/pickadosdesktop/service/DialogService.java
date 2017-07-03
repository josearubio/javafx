/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.service;

import javafx.scene.control.Alert;

/**
 *
 * @author Norman
 */
public final class DialogService {
    public static void showDialog(String title, String header, String content, Alert.AlertType DialogType) {
        Alert alert = new Alert(DialogType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}

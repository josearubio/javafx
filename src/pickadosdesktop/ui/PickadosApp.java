/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.ui;

import java.util.Properties;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JoseAntonio
 */
public class PickadosApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
	Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
	
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("table-view.css").toExternalForm());
	stage.setScene(scene);
	stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.setProperty("log4j.configurationFile", "resources/log4j2.xml");
        launch(args);
    }
}

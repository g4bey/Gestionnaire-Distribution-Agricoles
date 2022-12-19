package src;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import src.controllers.HomePageCtrl;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class GDA extends Application {
    /**
     * @param args
     */

    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("views/homePage.fxml"));
    	Scene scene = new Scene(root);
    	Image icon = new Image("src/ressources/pictures/1751148-ff5722.png");
    	
    	primaryStage.setTitle("Gestionnaire-Distribution-Agricoles");
    	primaryStage.setResizable(false);
    	primaryStage.getIcons().add(icon);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
}
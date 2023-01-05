import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class GDA extends Application {

	/**
	 * Méthode main du projet GDA.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		launch(args);
	} // main

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/views/homePage.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image("ressources/pictures/Logo.png");

		primaryStage.setTitle("Gestionnaire-Distributions-Agricoles");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.show();
	} // start

} // GDA
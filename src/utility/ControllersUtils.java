package utility;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
* Classe utilitaire pour les contrôleurs.
*
*/
public class ControllersUtils {

    private static Parent root;
    private static Stage stage;
    private static Scene scene;
    
    /**
    * Méthode qui charge une vue passée en paramètre.
    * @param event MouseEvent
    * @param path String Chemin vers la vue
    */
    public void loadView(ActionEvent event, String path) {
    	try {
            root = FXMLLoader.load(getClass().getResource(path));
        }
    	catch (IOException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    /**
    * Méthode qui charge une vue passée en paramètre.
    * @param event MouseEvent
    * @param path String Chemin vers la vue
    */
    public void loadView(MouseEvent event, String path) {
    	try {
            root = FXMLLoader.load(getClass().getResource(path));
        }
    	catch (IOException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    /**
    * Méthode qui charge une vue popup passée en paramètre.
    * @param event ActionEvent
    * @param path String Chemin vers la vue
    */
    public void loadPopup(ActionEvent event, String path) {
    	try {
    	    root = FXMLLoader.load(getClass().getResource(path));
        } 
        catch (IOException e) {
    			// TODO Auto-generated catch block
            e.printStackTrace();
        }
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
    
    /**
    * Méthode qui charge une vue popup passée en paramètre.
    * @param event MouseEvent
    * @param path String Chemin vers la vue
    */
    public void loadPopup(MouseEvent event, String path) {
        try {
     	    root = FXMLLoader.load(getClass().getResource(path));
        } 
        catch (IOException e) {
     			// TODO Auto-generated catch block
            e.printStackTrace();
        }
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
	
	/**
    * Méthode qui permet de fermer la vue popup.
    * @param event ActionEvent
    */
    public static void closePopup(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
}
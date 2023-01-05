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
import javafx.stage.WindowEvent;

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
    } // loadView

    /**
    * Méthode qui charge une vue pop-up passée en paramètre.
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
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    } // loadPopup

    /**
    * Méthode qui charge une vue pop-up passée en paramètre.
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
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    } // loadPopup

	/**
    * Méthode qui permet de fermer la vue pop-up et avertir le parent de sa fermeture.
    * @param event ActionEvent
    */
    public static void closePopupAndUpdateParent(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        notifcationFermetureFenetre(stage);
    	stage.close();
    } // closePopupAndUpdateParent

    /**
     * Méthode qui permet de fermer la vue popup.
     * @param event ActionEvent
     */
    public static void closePopup(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    } // closePopup

    /**
     * Permet de signaler au parent la fermeture d'une fenêtre.
     * @param stage la fenetre qui provoque cette notification.
     */
    public static void notifcationFermetureFenetre(Stage stage) {
        // Si on appel d'une pop-up,
        if (stage.getOwner().getOnCloseRequest() != null) {
            stage.getOwner().getOnCloseRequest().handle(
                    new WindowEvent(stage.getOwner(), WindowEvent.WINDOW_CLOSE_REQUEST)
            ); // handle
        } // if
    } // notificationFermetureFenetre

    public static Stage getStage() {
        return stage;
    } // getStage

} // ControllersUtils
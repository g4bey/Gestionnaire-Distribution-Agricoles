package src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
* Contrôleur permettant la consultation Administrateur.
*/

public class AdminConsultCtrl implements Initializable {

    @FXML
	private Label adminLoginLabel;
	
    @FXML
	private Rectangle adminProfileBtn;
	
    @FXML
	private Button addAdminBtn;
	
    @FXML
	private TableView adminTableView;
	
    @FXML
	private Button addProdBtn;
	
    @FXML
	private Button modifyProdBtn;
	
    @FXML
	private Button deleteProdBtn;
	
    @FXML
	private TableView prodTableView;
	
    @FXML
	private Button addClientBtn;
	
    @FXML
	private Button modifyClientBtn;
	
    @FXML
	private Button deleteClientBtn;
	
    @FXML
	private TableView clientTableView;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        modifyProdBtn.setDisable(false);
        deleteProdBtn.setDisable(false);
        modifyClientBtn.setDisable(false);
        deleteClientBtn.setDisable(false);
    }
	
    /**
     * Méthode qui ouvre une popup pour l'ajout d'un administrateur.
     * @param event
     * @throws IOException 
     */
    public void addAdmin(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/addAdmin.fxml"));
    	scene = new Scene(root);
    	stage = new Stage();
    	stage.set
    	
    	stage.setScene(scene);
    	stage.show();
    }
	
}
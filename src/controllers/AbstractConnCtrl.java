package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.AdministrateurDAO;
import DAO.ClientDAO;
import DAO.CommandeDAO;
import DAO.ProducteurDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
import utility.DatabaseConnection;

/**
 * Les controllers nécessitant une connexion à la BDD dérivent de ce controleur.
 * On n'instancie les DAO qu'une seule fois, ainsi ces attributs sont statiques.
 */
public abstract class AbstractConnCtrl {
    protected static Connection conn = null;
    protected static AdministrateurDAO aDAO = null;
    protected static ProducteurDAO pDAO = null;
    protected static ClientDAO cltDAO = null;
    protected static CommandeDAO commDAO = null;
    protected static TourneeDAO tDAO = null;
    protected static VehiculeDAO vDAO = null;

    /**
     * On instancie les attributs que si c'est le premier appel d'une classe fille.
     */
    public AbstractConnCtrl() {
        if(conn == null) {
            instantiateAttributes();
        }
    }

    /**
     * Instancie les DAO ainsi que la connection à la base donnée.
     */
    public void instantiateAttributes() {
        try {
            conn = DatabaseConnection.getInstance("production");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            return;
        }

        aDAO = new AdministrateurDAO(conn);
        pDAO = new ProducteurDAO(conn);
        cltDAO = new ClientDAO(conn);
        commDAO = new CommandeDAO(conn);
        tDAO = new TourneeDAO(conn);
        vDAO = new VehiculeDAO(conn);
        tDAO = new TourneeDAO(conn);
    }
}

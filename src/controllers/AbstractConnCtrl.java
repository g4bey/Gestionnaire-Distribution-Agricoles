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

public abstract class AbstractConnCtrl {
    protected Connection conn = null;
    protected AdministrateurDAO aDAO = null;
    protected ProducteurDAO pDAO = null;
    protected ClientDAO cltDAO = null;
    protected CommandeDAO commDAO = null;
    protected TourneeDAO tDAO = null;
    protected VehiculeDAO vDAO = null;

    public AbstractConnCtrl() {
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
    }
}

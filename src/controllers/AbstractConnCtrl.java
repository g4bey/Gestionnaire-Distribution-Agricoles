package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.AdministrateurDAO;
import utility.DatabaseConnection;

public abstract class AbstractConnCtrl {
    protected Connection conn = null;
    protected AdministrateurDAO aDAO = null;

    public AbstractConnCtrl() {
        try {
            conn = DatabaseConnection.getInstance("production");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            return;
        }

        aDAO = new AdministrateurDAO(conn);
    }
}

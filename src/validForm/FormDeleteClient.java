package validForm;

import DAO.ClientDAO;
import DAO.CommandeDAO;
import modele.Client;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class FormDeleteClient extends FormValidator {
    public  FormDeleteClient(Client client) {
        CommandeDAO cmdDAO;
        ClientDAO cltDAO;

        try {
            cmdDAO = new CommandeDAO(DatabaseConnection.getInstance("production"));
            cltDAO = new ClientDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        }

        // Il faut qu'il soit associé à aucune commande.
        if (cmdDAO.getAll().stream().noneMatch(
                cmd->cmd.getClient().equals(client))) {
            cltDAO.delete(client);
        } else {
            setInvalid("Ce client est associé à une commande.");
        }
    }
}

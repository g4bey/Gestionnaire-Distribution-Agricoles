package validForm;

import DAO.ClientDAO;
import DAO.TourneeDAO;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Validateur de formulaire pour modifier un Client.
 * 
 * @see controllers.ModifyClientCtrl
 */
public class FormModifyClientValidator extends FormClientValidator {

    public String adresseCsv;
    public String coordsXY;

    /**
     * Constructeur de FormClientAddValidator.
     * Permet de changer les champs d'adresse en une adresse au format CSV
     * et génère les coordonnées GPS
     *
     * @param idClient        Id du Client récupéré dans la vue
     * @param clientName      Nom du Client récupéré dans la vue
     * @param addressNumField Numéro de voie du Client récupéré dans la vue
     * @param addressPathType Type de voie du Client récupéré dans la vue
     * @param addressPathName Nom de voie du Client récupéré dans la vue
     * @param addressTownName Nom de ville du Client récupéré dans la vue
     * @param addressPostCode Code postal du Client récupéré dans la vue
     * @param clientPhone     Numéro de téléphone du Client récupéré dans la vue
     */
    public FormModifyClientValidator(int idClient, String clientName, String addressNumField, String addressPathType,
                                     String addressPathName, String addressTownName, String addressPostCode, String clientPhone) throws SQLException {

        super(clientName, addressNumField, addressPathType,
                addressPathName, addressTownName, addressPostCode, clientPhone);

        // Si jamais l'appel précédent n'arrive pas à bout.
        if (!this.isValid()) {
            return;
        } // if

        TourneeDAO tDAO;
        ClientDAO cDAO;
        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
            cDAO = new ClientDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        } // try/catch

        // Si on ne modifie pas l'adresse, il n'y a pas d'incohérence.
        if (!super.getAdresseCSV().equals(cDAO.get(idClient).getAdresseClient())) {
            // Si on modifie l'adresse, il faut être sûr que le Client n'est pas concerné par une Tournée.
            if (tDAO.clientEstDansTournee(idClient)) {
                setInvalid("Impossible de modifier l'adresse d'un Client dans une Tournée.");
            } // if
        } // if
    } // constructeur

} // FormModifyClientValidator
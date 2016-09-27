package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    TextField contactName;

    @FXML
    TextField contactNumber;

    @FXML
    TextField contactEmail;

    @FXML
    ListView list;

    public void addContact() {
        if (!contactEmail.getText().isEmpty() || !contactNumber.getText().isEmpty() || !contactName.getText().isEmpty()) {
            Contact contact = new Contact(contactName.getText(), contactNumber.getText(), contactEmail.getText());
            contacts.add(contact);
            contactName.clear();
            contactNumber.clear();
            contactEmail.clear();
        }
        else {
            // found documentation and working example of this code here: http://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("You done fucked up!");
            alert.setHeaderText("Who actually knows all of this about any contact that they know????!!!!!");
            alert.setContentText("ITS OVER 9000!!!!");
            alert.showAndWait();
        }
    }

    public void removeContact() {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        if (contact != null) {
            contacts.remove(contact);
        }
    }

    public void jsonWriter() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }
}

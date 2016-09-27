package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    String contactName;

    @FXML
    String contactNumber;

    @FXML
    String contactEmail;

    public void addContact() {

    }

    public void removeContact() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

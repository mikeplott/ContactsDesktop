package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public File f = new File("My Contacts.json");
    //HashMap<String, ObservableList<Contact>> myContacts = new HashMap<>();
    ArrayList<Contact> userContacts = new ArrayList<>();
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    TextField contactName;

    @FXML
    TextField contactNumber;

    @FXML
    TextField contactEmail;

    @FXML
    ListView list;

    public void addContact() throws IOException {
        if (!contactEmail.getText().isEmpty() || !contactNumber.getText().isEmpty() || !contactName.getText().isEmpty()) {
            Contact contact = new Contact(contactName.getText(), contactNumber.getText(), contactEmail.getText());
            contacts.add(contact);
            userContacts.add(contact);
            jsonWriter(f);
            contactName.clear();
            contactNumber.clear();
            contactEmail.clear();
        } else {
            // found documentation and working example of this code here: http://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("You done fucked up!");
            alert.setHeaderText("Who actually knows all of this about any contact that they know????!!!!!");
            alert.setContentText("ITS OVER 9000!!!!");
            alert.showAndWait();
        }
    }

    public void removeContact() throws IOException {
        Contact contact = (Contact) list.getSelectionModel().getSelectedItem();
        if (contact != null) {
            contacts.remove(contact);
            jsonWriter(f);
        }
    }

    public void jsonWriter(File f) throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        ContactsWrapper cw = new ContactsWrapper();
        cw.myContacts = userContacts;
        String json = serializer.deep(true).serialize(cw.myContacts);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public ArrayList<Contact> jsonReader(File f) throws IOException {
        FileReader fr = new FileReader(f);
        int fileSize = (int) this.f.length();
        char[] contents = new char[fileSize];
        fr.read(contents, 0, fileSize);
        JsonParser parser = new JsonParser();
        return parser.parse(contents, ArrayList.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userContacts = jsonReader(f);
            for (int i = 0; i < userContacts.size(); i++) {
                contacts.add(userContacts.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            list.setItems(contacts);
        }
    }









//        try {
//            ArrayList<Contact> theContacts = jsonReader(f);
//            for (int i = 0; i < theContacts.size(); i++) {
//                String cName = theContacts.get(i).name;
//                String cNumber = theContacts.get(i).phoneNumber;
//                String cEmail = theContacts.get(i).email;
//                Contact contact = new Contact(cName, cNumber, cEmail);
//                contacts.add(contact);
//                System.out.println(contacts);
//            }
//            if (contacts == null) {
//                contacts = FXCollections.observableArrayList();
//            }
// contacts = (ObservableList) theContacts;
//                for (int i = 0; i < theContacts.size(); i++) {
//                    contacts.add(theContacts.get(i));

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
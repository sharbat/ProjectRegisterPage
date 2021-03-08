package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button authSignInButton2;

    @FXML
    private TextField loginField2;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField locationField;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private RadioButton radioButtonFemale;
    @FXML
    void initialize() {

        authSignInButton2.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler=new DatabaseHandler();
        String firstName=nameField.getText();
        String lastName=lastnameField.getText();
        String userName=loginField2.getText();
        String password=passwordField2.getText();
        String location=locationField.getText();
        String gender="";
        if(radioButtonMale.isSelected()){
            gender="Male";
        }else
            gender="Female";
         User user=new User(firstName,lastName,userName,password,location,gender);
        dbHandler.signUpUser(user);
    }
}


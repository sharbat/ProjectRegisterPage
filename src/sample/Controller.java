package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sample.animation.*;



public class Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if(!loginText.equals("")&& !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }
            else
                System.out.println("Login and password are empty");
        });
        loginSignUpButton.setOnAction(event -> {
           openNewScene("/sample/signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
       // System.out.println(counter);
        if(counter >= 1){
            openNewScene("/sample/newWindow.fxml");
        }

       /*if(counter>=1) {
           try {
               while (result.next()) {
                   // Получаем имя пользователей
                   String name = result.getString("firstname");
                   String lastname = result.getString("lastname");
                   System.out.println("User name is - " + name + " " + lastname);
                   System.out.println(counter);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }else {
           ShakeIMP userLogin = new ShakeIMP(loginField);
           ShakeIMP userPassword=new ShakeIMP(passwordField);
           userLogin.plAnimation();
       }
*/

    }
    public void openNewScene(String window){
        loginSignUpButton.getScene().getWindow().hide();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch(
                IOException e)

        {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new

                Scene(root));
        stage.showAndWait();
    }
}


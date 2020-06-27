package pl.sda.budget.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.dbutils.ConnectionUtils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginScreenController implements Initializable {

    private MainScreenController mainScreenController;


    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    public void openApplication() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    public void exit() {
        Platform.exit();
        ConnectionUtils.closeConnection();
    }


    @FXML
    public void initialize() {
//        List<User> users = new ArrayList<>();
//users.add(new User(1,"Admin",null,"ad"));
//listPropertyUser=new SimpleListProperty<>();
//userObservableList= FXCollections.observableList(users);

    }

    @FXML
    private void setUserbyUserName() {
        String userName = loginField.getText();

        System.out.println(userName);
        System.out.println("jdhgkdgh");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
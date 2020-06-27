package pl.sda.budget.controllers;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import lombok.*;
import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.SQL.role.RoleFX;
import pl.sda.budget.SQL.role.RoleModel;
import pl.sda.budget.SQL.user.UserFX;
import pl.sda.budget.SQL.user.UserModel;
import pl.sda.budget.dialogs.DialogUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewUserScreenController implements Initializable {

    private MainScreenController mainScreenController;

    @FXML
    private javafx.scene.control.TextField userNameTextField;

    @FXML
    private javafx.scene.control.TextField passwordTextField;

    @FXML
    public ComboBox<RoleFX> roleComboBox;

    @FXML
    private javafx.scene.control.Button addNewUserButton;

    @FXML
    private Button backButton;

  @FXML
    private Label roleTextField;

    private UserModel userModel;

    private RoleModel roleModel;

    private UserFX userFX;


    public void backListUser() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/UsersViewScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsersViewScreenController usersViewScreenController = loader.getController();
        usersViewScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoleModel roleModel = new RoleModel();
        roleModel.init();

        this.userModel = new UserModel();
        this.roleComboBox.setItems(roleModel.getRoleList());

        this.userModel.userFXObjectPropertyProperty().get().userNameProperty().bind(this.userNameTextField.textProperty());
        this.userModel.userFXObjectPropertyProperty().get().passwordProperty().bind(this.passwordTextField.textProperty());
        this.userModel.userFXObjectPropertyProperty().get().roleProperty().bind(this.roleTextField.textProperty());

    }

    public void addUserOnAction() {

        this.userModel.saveUser(userNameTextField.getText(), passwordTextField.getText(),
                roleTextField.getText());
        backListUser();
    }
    public void onActionComboBox() {
        this.roleTextField.setText(String.valueOf(this.roleComboBox.getSelectionModel().getSelectedItem()));

    }
}

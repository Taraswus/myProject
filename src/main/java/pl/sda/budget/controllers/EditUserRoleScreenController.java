package pl.sda.budget.controllers;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.role.RoleFX;
import pl.sda.budget.SQL.role.RoleModel;
import pl.sda.budget.SQL.user.UserModel;
import pl.sda.budget.SQL.user.UserRepository;
import pl.sda.budget.dialogs.DialogUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditUserRoleScreenController implements Initializable {

    private MainScreenController mainScreenController;

    @FXML
    private javafx.scene.control.Label userNameTextField;

    @FXML
    private javafx.scene.control.TextField passwordTextField;

    @FXML
    public ComboBox<RoleFX> roleComboBox;

    @FXML
    private Label roleTextField;

    UserModel userModel = new UserModel();
    UsersViewScreenController usersViewScreenController = new UsersViewScreenController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoleModel roleModel = new RoleModel();
        roleModel.init();
        userModel.init();

        this.roleComboBox.setItems(roleModel.getRoleList());
        UsersViewScreenController usersViewScreenController = new UsersViewScreenController();
        userNameTextField.setText(getUserNameTextField().getText());
        System.out.println(getUserNameTextField().getText());
        System.out.println(userModel.getUserFXObjectProperty().getUserName());


        this.userModel.userFXObjectPropertyEditProperty().get().userNameProperty().bind(this.userNameTextField.textProperty());
        this.userModel.userFXObjectPropertyEditProperty().get().passwordProperty().bind(this.passwordTextField.textProperty());
        this.userModel.userFXObjectPropertyEditProperty().get().roleProperty().bind(this.roleTextField.textProperty());

    }

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


    public void onActionComboBox() {
        this.roleTextField.setText(String.valueOf(this.roleComboBox.getSelectionModel().getSelectedItem()));

    }

    public void editUserOnAction() {
        String newUserName= this.userModel.getUserFXObjectPropertyEdit().getUserName();
        String newPassword = this.userModel.getUserFXObjectPropertyEdit().getPassword();
        String newRole = this.userModel.getUserFXObjectPropertyEdit().getRole();
        System.out.println(newUserName);
        System.out.println(newPassword);
        System.out.println(newRole);

        userModel.editUserInDatabase();

    }

}

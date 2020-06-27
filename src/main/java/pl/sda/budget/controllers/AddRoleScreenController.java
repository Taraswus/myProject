package pl.sda.budget.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.role.RoleFX;
import pl.sda.budget.SQL.role.RoleModel;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsModel;
import pl.sda.budget.dialogs.DialogUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddRoleScreenController implements Initializable {

    private MainScreenController mainScreenController;
    @FXML
    private TextField addRoleNameTextField;
    @FXML
    private Button addRoleButton;
    @FXML
    private ComboBox<RoleFX> editRoleComboBox;
    @FXML
    private Button editRoleButton;
    @FXML
    private Button deleteRoleButton;


    private RoleModel roleModel;


    public void backMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/MenuScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.roleModel = new RoleModel();
        roleModel.init();
        this.editRoleComboBox.setItems(this.roleModel.getRoleList());
        initBindings();
    }

    public void addRoleOnAction() {

        roleModel.saveRole(addRoleNameTextField.getText());
        addRoleNameTextField.clear();
        roleModel.init();
        editRoleComboBox.setItems(roleModel.getRoleList());
    }

    private void initBindings() {

        this.addRoleButton.disableProperty().bind(addRoleNameTextField.textProperty().isEmpty());
        this.deleteRoleButton.disableProperty().bind(roleModel.roleProperty().isNull());
        this.editRoleButton.disableProperty().bind(roleModel.roleProperty().isNull());
    }

    public void deleteRoleOnAction() {
        this.roleModel.deleteRoleById();

    }

    public void onActionComboBox() {
        this.roleModel.setRole(this.editRoleComboBox.getSelectionModel().getSelectedItem());

    }

    public void editRoleOnAction() {
        String newRoleName = DialogUtils.editDialog(this.roleModel.getRole().getRoleName());
        if (newRoleName != null) {
            this.roleModel.getRole().setRoleName(newRoleName);

            this.roleModel.editRole();
        }

    }

}

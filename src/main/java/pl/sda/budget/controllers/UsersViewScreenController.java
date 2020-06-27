package pl.sda.budget.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.user.UserFX;
import pl.sda.budget.SQL.user.UserModel;
import pl.sda.budget.dialogs.DialogUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersViewScreenController implements Initializable {

    private MainScreenController mainScreenController;

    @FXML
    private TableView<UserFX> userTableView;

    @FXML
    private TableColumn<UserFX, String> userNameColumn;

    @FXML
    private TableColumn<UserFX, String> passwordColumn;

    @FXML
    private TableColumn<UserFX, String> roleColumn;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem editMenuItem;

    UserModel userModel = new UserModel();

//    EditUserRoleScreenController editUserRoleScreenController=new EditUserRoleScreenController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userModel.init();
        this.deleteMenuItem.disableProperty().bind(this.userTableView.getSelectionModel().selectedItemProperty().isNull());
        this.editMenuItem.disableProperty().bind(this.userTableView.getSelectionModel().selectedItemProperty().isNull());
        this.userTableView.setItems(userModel.getUserFXObservableList());
        this.userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        this.passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        this.roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        this.userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            userModel.setUserFXObjectPropertyEdit(newValue);
        });

    }

    @FXML
    public void openNewUser() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/NewUserScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NewUserScreenController newUserScreenController = loader.getController();
        newUserScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    public void editUser() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/EditUserRoleScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditUserRoleScreenController editUserScreenController = loader.getController();
        editUserScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);



    }


    public void deleteUserOnAction() {
        userModel.deleteUserInDataBase();
    }

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
}

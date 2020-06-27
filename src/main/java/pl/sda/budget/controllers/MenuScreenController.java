package pl.sda.budget.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.budget.dialogs.DialogUtils;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuScreenController implements Initializable {
    private MainScreenController mainScreenController;




    @FXML
    public void openAddInvoice() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/AddInvoiceScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddInvoiceScreenController addInvoiceScreenController = loader.getController();
        addInvoiceScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    public void openAddTypeOfCosts() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/AddTypeOfCostsScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddTypeOfCostsScreenController addTypeOfCostsScreenController = loader.getController();
        addTypeOfCostsScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    public void openAddRole() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/AddRoleScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddRoleScreenController addRoleScreenController = loader.getController();
        addRoleScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    public void openAddCurrency() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/AddCurrencyScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddCurrencyScreenController addcurrencyScreenController = loader.getController();
        addcurrencyScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }


    @FXML
    public void openAddStatement() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/AddStatementScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddStatementScereenController addStatenentScreenController = loader.getController();
        addStatenentScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
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
    public void openUsers() {

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
    @FXML
    public void exit() {
        Optional<ButtonType> result = DialogUtils.ConfirmationDialog();
        if(result.get()==ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

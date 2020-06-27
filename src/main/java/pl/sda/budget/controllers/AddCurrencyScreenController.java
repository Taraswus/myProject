package pl.sda.budget.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.currency.CurrencyFX;
import pl.sda.budget.SQL.currency.CurrencyModel;
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
public class AddCurrencyScreenController implements Initializable {

    private MainScreenController mainScreenController;
    @FXML
    private TextField addCurrencyNameTextField;
    @FXML
    private Button addCurrencyButton;
    @FXML
    private ComboBox<CurrencyFX> editCurrencyComboBox;
    @FXML
    private Button editCurrencyButton;
    @FXML
    private Button deleteCurrencyButton;

    private CurrencyModel currencyModel;

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
        this.currencyModel = new CurrencyModel();
        currencyModel.init();
        this.editCurrencyComboBox.setItems(this.currencyModel.getCurrencyList());
        initBindings();
    }

    public void addCurrencyOnAction() {
        currencyModel.saveCurrency(addCurrencyNameTextField.getText());
        addCurrencyNameTextField.clear();
        currencyModel.init();
        editCurrencyComboBox.setItems(currencyModel.getCurrencyList());
    }

    private void initBindings() {

        this.addCurrencyButton.disableProperty().bind(addCurrencyNameTextField.textProperty().isEmpty());
        this.deleteCurrencyButton.disableProperty().bind(currencyModel.currencyProperty().isNull());
        this.editCurrencyButton.disableProperty().bind(currencyModel.currencyProperty().isNull());
    }

    public void deleteCurrencyOnAction() {
        this.currencyModel.deleteCurrencyById();
    }

    public void onActionComboBox() {
        this.currencyModel.setCurrency(this.editCurrencyComboBox.getSelectionModel().getSelectedItem());
    }

    public void editCurrencyOnAction() {
        String newCurrencyName = DialogUtils.editDialog(this.currencyModel.getCurrency().getCurrencyName());
        if (newCurrencyName != null) {
            this.currencyModel.getCurrency().setCurrencyName(newCurrencyName);

            this.currencyModel.editCurrency();
        }

    }
}


package pl.sda.budget.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.currency.Currency;

import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsFX;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddInvoiceScreenController implements Initializable {

    private MainScreenController mainScreenController;
    @FXML
    private TextField documentNumber;
    @FXML
    private TextField documentDate;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField contractorTextField;
    @FXML
    private TextField amountInEURTextField;
    @FXML
    private ComboBox<TypeOfCostsFX> typeOfCostsComboBox;
    @FXML
    private Button buttonAddInvoiceOnAction;
    @FXML
    private TextField dateOfPaymentTextField;
    @FXML
    ComboBox<Currency> currencyComboBox;


    public void backMenu() {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TypeOfCostsModel typeOfCostsModel = new TypeOfCostsModel();
        typeOfCostsModel.init();
//        typeOfCostsComboBox.setItems(typeOfCostsModel.getTypeOfCostsList());
    }

//    public void addCostOnAction() {
//        InvoiceModel invoiceModel = new InvoiceModel();
//        invoiceModel.saveInvoice(documentNumber.getText()
//                , documentDate.getText()
//                , amountTextField.getText()
//                , contractorTextField.getText()
//                , amountInEURTextField.getText()
//
//                , dateOfPaymentTextField.getText())
//        ;
//        documentNumber.clear();
//    }
}

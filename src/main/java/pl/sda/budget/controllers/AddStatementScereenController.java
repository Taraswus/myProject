package pl.sda.budget.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.*;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AddStatementScereenController implements Initializable {
    private MainScreenController mainScreenController;
    @FXML
    private TextField statementNumber;
    @FXML
    private TextField statementDate;
    @FXML
    private TextField contractor;
    @FXML
    private TextField invoiceNumber;
    @FXML
    private TextField cost;
    @FXML
    private TextField currency;


    public void backMenu(){
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
    public void addStatementOnAction() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}



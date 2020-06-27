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
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsFX;
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
public class AddTypeOfCostsScreenController implements Initializable {

    private MainScreenController mainScreenController;
    @FXML
    private TextField addCostNameTextField;
    @FXML
    private Button addCostButton;
    @FXML
    private ComboBox<TypeOfCostsFX> editCostComboBox;
    @FXML
    private Button editCostButton;
    @FXML
    private Button deleteCostButton;

    private TypeOfCostsModel typeOfCostsModel;

    public void backMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/MenuScreen.fxml"));

        Pane pane = null;
        try {
            pane = loader.load();
        }  catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScrean(pane);
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.typeOfCostsModel = new TypeOfCostsModel();
        typeOfCostsModel.init();
        this.editCostComboBox.setItems(this.typeOfCostsModel.getTypeOfCostsList());
        initBindings();
    }

    public void addCostOnAction() {
        typeOfCostsModel.saveTypeOfCosts(addCostNameTextField.getText());
        addCostNameTextField.clear();
        typeOfCostsModel.init();
        editCostComboBox.setItems(typeOfCostsModel.getTypeOfCostsList());
    }

        private void initBindings() {

        this.addCostButton.disableProperty().bind(addCostNameTextField.textProperty().isEmpty());
        this.deleteCostButton.disableProperty().bind(typeOfCostsModel.typeOfCostsProperty().isNull());
        this.editCostButton.disableProperty().bind(typeOfCostsModel.typeOfCostsProperty().isNull());
    }

    public void deleteCostOnAction() {
        this.typeOfCostsModel.deleteTypeOfCostsById();

    }

    public void onActionComboBox() {
        this.typeOfCostsModel.setTypeOfCosts(this.editCostComboBox.getSelectionModel().getSelectedItem());

    }


    public void editCostOnAction() {
        String newTypeOfCost = DialogUtils.editDialog(this.typeOfCostsModel.getTypeOfCosts().gettypeOfCosts());
        if (newTypeOfCost != null) {
            this.typeOfCostsModel.getTypeOfCosts().settypeOfCosts(newTypeOfCost);

            this.typeOfCostsModel.editTypeOfCosts();
        }
    }
}

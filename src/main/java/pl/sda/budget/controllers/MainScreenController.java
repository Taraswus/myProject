package pl.sda.budget.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainScreenController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize() {
        loadMenuScreen();

    }
    public void loadMenuScreen(){
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/LoginScreen.fxml"));
        Pane pane = null;
        try {
             pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginScreenController menuScreen=loader.getController();
        menuScreen.setMainScreenController(this);
        setScrean(pane);
    }
public void setScrean(Pane pane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
}

}

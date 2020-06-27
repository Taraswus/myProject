package pl.sda.budget.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.dialogs.DialogUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App
        extends Application {
    public static void main(String[] args) {


        launch(args);


    }


    @Override
    public void start(Stage stage)  {
        Locale.setDefault(new Locale("pl"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXMLFiles/MainScreen.fxml"));
        ResourceBundle bundle=ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        StackPane stackpane = null;
        try {
            stackpane = loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        Scene scene = new Scene(stackpane, 800, 600);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("tittle.application"));
        stage.setResizable(true);
        stage.show();
        ConnectionUtils.initDatabase();

    }
}
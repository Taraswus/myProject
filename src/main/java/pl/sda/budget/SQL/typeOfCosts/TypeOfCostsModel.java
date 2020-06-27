package pl.sda.budget.SQL.typeOfCosts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.SQL.dbutils.ConnectionUtils;
import pl.sda.budget.controllers.AddTypeOfCostsScreenController;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

@Setter
@Getter
public class TypeOfCostsModel {

    private ObservableList<TypeOfCostsFX> typeOfCostsList = FXCollections.observableArrayList();
    private ObjectProperty<TypeOfCostsFX> typeOfCosts = new SimpleObjectProperty<>();

    public ObservableList<TypeOfCostsFX> getTypeOfCostsList() {
        return typeOfCostsList;
    }

    public void setTypeOfCostsList(ObservableList<TypeOfCostsFX> typeOfCostsList) {
        this.typeOfCostsList = typeOfCostsList;
    }

    public TypeOfCostsFX getTypeOfCosts() {
        return typeOfCosts.get();
    }

    public ObjectProperty<TypeOfCostsFX> typeOfCostsProperty() {
        return typeOfCosts;
    }

    public void setTypeOfCosts(TypeOfCostsFX typeOfCosts) {
        this.typeOfCosts.set(typeOfCosts);
    }

    public void init() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            TypeOfCostsRepository typeOfCostsRepository = new TypeOfCostsRepository(conn);
            List<TypeOfCosts> costs = typeOfCostsRepository.getAll();

            this.typeOfCostsList.clear();
            costs.forEach(typeOfCosts -> {
                TypeOfCostsFX typeOfCostsFX = new TypeOfCostsFX();
                typeOfCostsFX.setId(typeOfCosts.getId());
                typeOfCostsFX.settypeOfCosts(typeOfCosts.getTypeOfCosts());
                typeOfCostsList.add(typeOfCostsFX);
            });


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void saveTypeOfCosts(String typeOfCosts) {
        try {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                TypeOfCostsRepository typeOfCostsRepository = new TypeOfCostsRepository(conn);
                typeOfCostsRepository.getAll();
                List<TypeOfCosts> costs = Arrays.asList(
                        TypeOfCosts.TypeOfCostsBuilder.builder().typeOfCosts(typeOfCosts).build());

                typeOfCostsRepository.save(costs);
            }
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }
    public void editTypeOfCosts() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            TypeOfCostsRepository typeOfCostsRepository = new TypeOfCostsRepository(conn);

            Optional<TypeOfCosts> tmptTypeOfCosts = typeOfCostsRepository.findOneById(typeOfCosts.getValue().getId());

            tmptTypeOfCosts.get().setTypeOfCosts(typeOfCosts.getValue().gettypeOfCosts());

            typeOfCostsRepository.update(tmptTypeOfCosts);


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

    public void deleteTypeOfCostsById() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            TypeOfCostsRepository typeOfCostsRepository = new TypeOfCostsRepository(conn);

            typeOfCostsRepository.delete(typeOfCosts.getValue().getId());


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

}


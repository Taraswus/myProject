package pl.sda.budget.SQL.typeOfCosts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;


public class TypeOfCostsFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty typeOfCosts = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String gettypeOfCosts() {
        return typeOfCosts.get();
    }
    public StringProperty roleNameProperty() {
        return typeOfCosts;
    }
    public void settypeOfCosts(String typeOfCosts) {
        this.typeOfCosts.set(typeOfCosts);
    }
    @Override
    public String toString() {
        return typeOfCosts.getValue() ;
    }
}

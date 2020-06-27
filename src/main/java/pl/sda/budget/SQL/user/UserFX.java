package pl.sda.budget.SQL.user;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.util.StringConverter;
import pl.sda.budget.SQL.role.RoleFX;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty Role = new SimpleStringProperty();






    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return Role.get();
    }

    public StringProperty roleProperty() {
        return Role;
    }

    public void setRole(String role) {
        this.Role.set(role);
    }
}

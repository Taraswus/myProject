package pl.sda.budget.SQL.user;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.SQL.role.RoleFX;
import pl.sda.budget.SQL.role.RoleRepository;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

@Getter
@Setter
public class UserModel {
    private ObjectProperty<UserFX> userFXObjectProperty = new SimpleObjectProperty<>(new UserFX());
    private ObjectProperty<UserFX> userFXObjectPropertyEdit = new SimpleObjectProperty<>(new UserFX());
    private ObservableList<UserFX> userFXObservableList = FXCollections.observableArrayList();


    public void init() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            UserRepository userRepository = new UserRepository(conn);

            List<User> userList = userRepository.getAll();
            this.userFXObservableList.clear();
            userList.forEach(user -> {
                UserFX userFX = new UserFX();
                userFX.setId(user.getId());
                userFX.setUserName(user.getUserName());
                userFX.setPassword(user.getPassword());
                userFX.setRole(user.getRole());
                userFXObservableList.add(userFX);
            });


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }


    public void saveUser(String userName, String password, String role) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            UserRepository userRepository = new UserRepository(conn);
            List<User> users = Arrays.asList(
                    User.UserBuilder.builder().userName(userName).password(password).role(role).build());

            userRepository.save(users);


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
    public void editUserInDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            UserRepository userRepository = new UserRepository(conn);

            Optional<User> tmptUser = userRepository.findOneById(userFXObjectPropertyEdit.getValue().getId());

            tmptUser.get().setUserName(userFXObjectPropertyEdit.getValue().getUserName());
            tmptUser.get().setPassword(userFXObjectPropertyEdit.getValue().getPassword());
            tmptUser.get().setRole(userFXObjectPropertyEdit.getValue().getRole());

            userRepository.update(tmptUser);


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

    public void deleteUserInDataBase(){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            UserRepository userRepository = new UserRepository(conn);
           userRepository.delete(getUserFXObjectPropertyEdit().getId());
           init();


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }


    public UserFX getUserFXObjectProperty() {
        return userFXObjectProperty.get();
    }

    public ObjectProperty<UserFX> userFXObjectPropertyProperty() {
        return userFXObjectProperty;
    }

    public void setUserFXObjectProperty(UserFX userFXObjectProperty) {
        this.userFXObjectProperty.set(userFXObjectProperty);
    }

    public ObservableList<UserFX> getUserFXObservableList() {
        return userFXObservableList;
    }

    public void setUserFXObservableList(ObservableList<UserFX> userFXObservableList) {
        this.userFXObservableList = userFXObservableList;
    }

    public UserFX getUserFXObjectPropertyEdit() {
        return userFXObjectPropertyEdit.get();
    }

    public ObjectProperty<UserFX> userFXObjectPropertyEditProperty() {
        return userFXObjectPropertyEdit;
    }

    public void setUserFXObjectPropertyEdit(UserFX userFXObjectPropertyEdit) {
        this.userFXObjectPropertyEdit.set(userFXObjectPropertyEdit);
    }
}
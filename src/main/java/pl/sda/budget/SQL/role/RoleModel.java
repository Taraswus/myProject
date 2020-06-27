package pl.sda.budget.SQL.role;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

@Setter
@Getter
public class RoleModel {

    private ObservableList<RoleFX> roleList = FXCollections.observableArrayList();
    private ObjectProperty<RoleFX> role = new SimpleObjectProperty<>();



    public void setRoleList(ObservableList<RoleFX> roleList) {
        this.roleList = roleList;
    }

    public RoleFX getRole() {
        return role.get();
    }

    public ObjectProperty<RoleFX> roleProperty() {
        return role;
    }

    public void setRole(RoleFX role) {
        this.role.set(role);

    }

    public void init() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            RoleRepository roleRepository = new RoleRepository(conn);
            List<Role> roles = roleRepository.getAll();

            this.roleList.clear();
            roles.forEach(role -> {
                RoleFX roleFX = new RoleFX();
                roleFX.setId(role.getId());
                roleFX.setRoleName(role.getRoleName());
                roleList.add(roleFX);
            });


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void saveRole(String role) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            RoleRepository roleRepository = new RoleRepository(conn);
            roleRepository.getAll();
            List<Role> roles = Arrays.asList(
                    Role.RoleBuilder.builder().roleName(role).build());

            roleRepository.save(roles);


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

    public void editRole() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            RoleRepository roleRepository = new RoleRepository(conn);

            Optional<Role>tmptRole = roleRepository.findOneById(role.getValue().getId());

                  tmptRole.get().setRoleName(role.getValue().getRoleName());

            roleRepository.update(tmptRole);


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

    public void deleteRoleById() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            RoleRepository roleRepository = new RoleRepository(conn);

            roleRepository.delete(role.getValue().getId());


        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        init();
    }

}


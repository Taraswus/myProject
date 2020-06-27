package pl.sda.budget.SQL.dbutils;


import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;

import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.mysql.cj.jdbc.Driver;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import pl.sda.budget.SQL.currency.CurrencyRepository;
import pl.sda.budget.SQL.currency.CurrencyTable;
import pl.sda.budget.SQL.role.Role;
import pl.sda.budget.SQL.role.RoleRepository;
import pl.sda.budget.SQL.role.RoleTable;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsModel;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsRepository;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCostsTable;
import pl.sda.budget.SQL.user.User;
import pl.sda.budget.SQL.user.UserRepository;
import pl.sda.budget.SQL.user.UserTable;
import pl.sda.budget.dialogs.DialogUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConnectionUtils {

    public static void initDatabase() {
        createConnect();


    }

    private static void createConnect() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            new TypeOfCostsTable(conn).createTable();
            TypeOfCostsRepository typeOfCostsRepository = new TypeOfCostsRepository(conn);
            new RoleTable(conn).createTable();
            RoleRepository roleRepository = new RoleRepository(conn);
            if (roleRepository.getAll().isEmpty()) {
                roleRepository.save(Role.RoleBuilder.builder().id(1).roleName("Admin").build());
            }
            new UserTable(conn).createTable();
            UserRepository userRepository = new UserRepository(conn);
           if (userRepository.getAll().isEmpty()) {
                userRepository.save(User.UserBuilder.builder().id(1).userName("Administrator").password(null).role("Admin").build());
           }
            new CurrencyTable(conn).createTable();
            CurrencyRepository currencyRepository= new CurrencyRepository(conn);



        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public static void closeConnection() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.close();
        }  catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public static void enableAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(true);
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }

    public static void disableAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(false);
        }catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }
}
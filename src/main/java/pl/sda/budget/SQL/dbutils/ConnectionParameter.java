package pl.sda.budget.SQL.dbutils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

@Builder
public class ConnectionParameter {

        public static final String URL = "jdbc:mysql://localhost:3306/budgetfx?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
        public static final String USER = "root";
        public static final String PASSWORD = "";
}

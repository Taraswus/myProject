package pl.sda.budget.SQL.user;

import lombok.*;
import pl.sda.budget.SQL.role.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    int id;
    String userName;
    String password;
    String role;

    public static class UserBuilder {
        int id;
        String userName;
        String password;
        String role;

        private UserBuilder() {
        }

        private UserBuilder(User user) {
            this.id = user.getId();
            this.userName = user.getUserName();
            this.password = user.getPassword();
            this.role = user.getRole();

        }

        public static User.UserBuilder builder() {
            return new UserBuilder();
        }

        public static User.UserBuilder builder(User user) {
            return new User.UserBuilder(user);
        }

        public User.UserBuilder id(int id) {
            this.id = id;
            return this;
        }


        public User.UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public User.UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User.UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id, userName, password, role);
        }
    }
}
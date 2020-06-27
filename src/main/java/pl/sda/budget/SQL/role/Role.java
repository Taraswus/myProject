package pl.sda.budget.SQL.role;

import lombok.*;
import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    int id;
    String roleName;

    public static class RoleBuilder {
        int id;
        String roleName;


        private RoleBuilder() {
        }

        private RoleBuilder(Role role) {
            this.id = role.getId();
            this.roleName = role.getRoleName();
        }

        public static RoleBuilder builder() { return new RoleBuilder(); }

        public static RoleBuilder builder(Role role){return new RoleBuilder(role);}

        public RoleBuilder roleBuilder (String roleName){
            this.roleName=roleName;
            return this;
        }

        public Role build(){
            return new Role(id, roleName);
        }
    }
}

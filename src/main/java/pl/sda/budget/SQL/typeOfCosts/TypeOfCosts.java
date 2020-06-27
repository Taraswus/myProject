package pl.sda.budget.SQL.typeOfCosts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfCosts {
    int id;
    String typeOfCosts;

    public static class TypeOfCostsBuilder {
        int id;
        String typeOfCosts;

        private TypeOfCostsBuilder() {
        }

        private TypeOfCostsBuilder(TypeOfCosts typeOfCosts) {
            this.id = typeOfCosts.getId();
            this.typeOfCosts = typeOfCosts.getTypeOfCosts();
        }

        public static TypeOfCostsBuilder builder() {
            return new TypeOfCostsBuilder();
        }

        public static TypeOfCostsBuilder builder(TypeOfCosts typeOfCosts) {
            return new TypeOfCostsBuilder(typeOfCosts); }

        public TypeOfCostsBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TypeOfCostsBuilder typeOfCosts(String typeOfCosts) {
            this.typeOfCosts = typeOfCosts;
            return this;
        }

        public TypeOfCosts build() {
            return new TypeOfCosts(id, typeOfCosts);
        }
    }
}
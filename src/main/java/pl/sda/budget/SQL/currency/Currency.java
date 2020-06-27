package pl.sda.budget.SQL.currency;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {
    int id;
    String currencyName;
    public static class CurrencyBuilder {
        int id;
        String currencyName;
        private CurrencyBuilder() {
        }
        private CurrencyBuilder(Currency currency) {
            this.id = currency.getId();
            this.currencyName = currency.getCurrencyName();
        }
        public static CurrencyBuilder builder() { return new CurrencyBuilder();        }
        public static CurrencyBuilder builder(Currency currency) {
            return new CurrencyBuilder(currency);
        }
        public CurrencyBuilder currencyBuilder(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }
        public Currency build() {
            return new Currency(id, currencyName);
        }
    }
}

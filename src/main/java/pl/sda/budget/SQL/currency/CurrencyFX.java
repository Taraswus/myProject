package pl.sda.budget.SQL.currency;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty currencyName = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String getCurrencyName() {
        return currencyName.get();
    }
    public StringProperty currencyNameProperty() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName.set(currencyName);
    }
    @Override
    public String toString() {
        return currencyName.getValue() ;
    }
}


package pl.sda.budget.SQL.invoice;

import javafx.beans.property.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceFX {
    IntegerProperty id=new SimpleIntegerProperty();
    StringProperty documentNumber=new SimpleStringProperty();
    StringProperty documentDate=new SimpleStringProperty();
    StringProperty dateOfPayment=new SimpleStringProperty();
    StringProperty amount=new SimpleStringProperty();
    StringProperty contractor=new SimpleStringProperty();
    StringProperty amountInEUR=new SimpleStringProperty();
    IntegerProperty idTypeOfCosts=new SimpleIntegerProperty();
    IntegerProperty idCurrency=new SimpleIntegerProperty();

}

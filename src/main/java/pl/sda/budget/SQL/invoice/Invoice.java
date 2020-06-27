package pl.sda.budget.SQL.invoice;

import lombok.*;
import pl.sda.budget.SQL.currency.Currency;

import pl.sda.budget.SQL.typeOfCosts.TypeOfCosts;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    int id;
    String documentNumber;
    Date documentDate;
    Date dateOfPayment;
    BigDecimal amount;
    String contractor;
    BigDecimal amountInEUR;
    int idTypeOfCosts;
    int idCurrency;

    public static class InvoiceBuilder {
        int id;
        String documentNumber;
        Date documentDate;
        Date dateOfPayment;
        BigDecimal amount;
        String contractor;
        BigDecimal amountInEUR;
        int idTypeOfCosts;
        int idCurrency;



        private InvoiceBuilder() {
        }

        private InvoiceBuilder(Invoice invoice) {

            this.id = invoice.getId();
            this.documentNumber = invoice.getDocumentNumber();
            this.documentDate = invoice.getDocumentDate();
            this.dateOfPayment = invoice.getDateOfPayment();
            this.amount = invoice.getAmount();
            this.contractor = invoice.getContractor();
            this.amountInEUR = invoice.getAmountInEUR();
            this.idTypeOfCosts = invoice.getIdTypeOfCosts();
            this.idCurrency = invoice.getIdCurrency();


        }

        public static InvoiceBuilder builder() {
            return new InvoiceBuilder();
        }


        public static InvoiceBuilder builder(Invoice invoice) {
            return new InvoiceBuilder(invoice);
        }


        public InvoiceBuilder id(int id) {
            this.id = id;
            return this;
        }

        public InvoiceBuilder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }
        public InvoiceBuilder documentDate (Date documentDate){
            this.documentDate=documentDate;
            return this;
        }
        public InvoiceBuilder dateOfPayment(Date dateOfPayment) {
            this.dateOfPayment = dateOfPayment;
            return this;
        }
        public InvoiceBuilder amount (BigDecimal amount){
            this.amount= amount;
            return this;
        }
      public InvoiceBuilder contractor (String contractor){
            this.contractor= contractor;
            return this;
      }
      public InvoiceBuilder amountInEUR(BigDecimal amountInEUR){
            this.amountInEUR=amountInEUR;
            return this;
      }
        public InvoiceBuilder typeOfCosts (int idTypeOfCosts){
            this.idTypeOfCosts=idTypeOfCosts;
            return this;
        }
        public InvoiceBuilder currency (int idCurrency){
            this.idCurrency=idCurrency;
            return this;
        }
        public Invoice build() {
            return new Invoice(id, documentNumber,documentDate,dateOfPayment,amount,contractor,amountInEUR
                    ,idTypeOfCosts,idCurrency);
        }


    }

}
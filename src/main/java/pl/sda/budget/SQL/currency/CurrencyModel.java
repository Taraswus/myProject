package pl.sda.budget.SQL.currency;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import pl.sda.budget.dialogs.DialogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static pl.sda.budget.SQL.dbutils.ConnectionParameter.*;

@Setter
@Getter
public class CurrencyModel {

    private ObservableList<CurrencyFX> currencyList = FXCollections.observableArrayList();
    private ObjectProperty<CurrencyFX> currency = new SimpleObjectProperty<>();

    public ObservableList<CurrencyFX> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(ObservableList<CurrencyFX> currencyList) {
        this.currencyList = currencyList;
    }

    public CurrencyFX getCurrency() {
        return currency.get();
    }

    public ObjectProperty<CurrencyFX> currencyProperty() {
        return currency;
    }

    public void setCurrency(CurrencyFX currency) {
        this.currency.set(currency);
    }

    public void init() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            CurrencyRepository currencyRepository = new CurrencyRepository(conn);
            List<Currency> currencies = currencyRepository.getAll();

            this.currencyList.clear();
            currencies.forEach(currency -> {
                CurrencyFX currencyFX = new CurrencyFX();
                currencyFX.setId(currency.getId());
                currencyFX.setCurrencyName(currency.getCurrencyName());
                currencyList.add(currencyFX);
            });

        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void saveCurrency(String currency) {

        try {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                CurrencyRepository currencyRepository = new CurrencyRepository(conn);
                currencyRepository.getAll();
                List<Currency> currencies = Arrays.asList(
                        Currency.CurrencyBuilder.builder().currencyName(currency).build());

                currencyRepository.save(currencies);
            }
        } catch (Exception throwables) {
            DialogUtils.errorDialog(throwables.getMessage());
        }
        init();
    }

    public void editCurrency() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            CurrencyRepository currencyRepository = new CurrencyRepository(conn);

            Optional<Currency> tmptCurrency = currencyRepository.findOneById(currency.getValue().getId());

            tmptCurrency.get().setCurrencyName(currency.getValue().getCurrencyName());

            currencyRepository.update(tmptCurrency);


        } catch (Exception throwables) {
            DialogUtils.errorDialog(throwables.getMessage());
        }
        init();
    }

    public void deleteCurrencyById() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            CurrencyRepository currencyRepository = new CurrencyRepository(conn);

            currencyRepository.delete(currency.getValue().getId());


        } catch (Exception throwables) {
            DialogUtils.errorDialog(throwables.getMessage());
        }
        init();
    }
}
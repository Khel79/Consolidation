package app.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class AgressoRecord {
    private SimpleStringProperty group;
    private SimpleStringProperty primeAccountNumber;
    private SimpleStringProperty primeAccountName;
    private SimpleStringProperty agressoAccountNumber;
    private SimpleStringProperty agressoAccountName;
    private SimpleDoubleProperty amount;
    private SimpleStringProperty valuta;
    private SimpleStringProperty country;
    private SimpleDoubleProperty amountInEuro;
    private SimpleStringProperty mainCategory;
    private SimpleStringProperty subCategory;
    private SimpleStringProperty year;
    private SimpleStringProperty quarter;
    private SimpleDoubleProperty conversionRate;

    public AgressoRecord(String group, String primeAccountNumber, String primeAccountName, String agressoAccountNumber,
                         String agressoAccountName, String amount, String valuta, String year, String quarter, double conversionRate) {
        this.group = new SimpleStringProperty(group);
        this.primeAccountNumber = new SimpleStringProperty(primeAccountNumber);
        this.primeAccountName = new SimpleStringProperty(primeAccountName);
        this.agressoAccountNumber = new SimpleStringProperty(agressoAccountNumber);
        this.agressoAccountName = new SimpleStringProperty(agressoAccountName);
        setAmount(amount);
        this.valuta = new SimpleStringProperty(valuta);
        this.country = new SimpleStringProperty(determineCountry());
        this.year = new SimpleStringProperty(year);
        this.quarter = new SimpleStringProperty(quarter);
        this.conversionRate = new SimpleDoubleProperty(conversionRate);
        setAmountInEuro();

    }

    public String determineCountry() {
        String country = "";
        switch (getValuta()) {
            case "EUR": country = "Belgium"; break;
            case "KES": country = "Kenya"; break;
            case "MYR": country = "Malaysia"; break;
            case "USD": country = "United States"; break;
        }
        return country;
    }

    public void setAmount(String amount) {
        amount = amount.replace(".", "");
        amount = amount.replace(",", ".");
        this.amount = new SimpleDoubleProperty(Double.parseDouble(amount));
    }

    public void setAmountInEuro() {
        double result = 0;
        switch (getValuta()) {
            case "EUR": result = amount.getValue(); break;
            case "KES": result = amount.getValue() * conversionRate.getValue(); break;
            case "MYR": result = amount.getValue() * conversionRate.getValue(); break;
            case "USD": result = amount.getValue() * conversionRate.getValue(); break;
        }
        this.amountInEuro = new SimpleDoubleProperty(result);
    }

    public String getGroup() {
        return group.get();
    }

    public void setGroup(String group) {
        this.group.set(group);
    }


    public String getPrimeAccountName() {
        return primeAccountName.get();
    }

    public void setPrimeAccountName(String primeAccountName) {
        this.primeAccountName.set(primeAccountName);
    }

    public String getAgressoAccountName() {
        return agressoAccountName.get();
    }

    public void setAgressoAccountName(String agressoAccountName) {
        this.agressoAccountName.set(agressoAccountName);
    }

    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public String getValuta() {
        return valuta.get();
    }

    public void setValuta(String valuta) {
        this.valuta.set(valuta);
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public String getPrimeAccountNumber() {
        return primeAccountNumber.get();
    }

    public SimpleStringProperty primeAccountNumberProperty() {
        return primeAccountNumber;
    }

    public void setPrimeAccountNumber(String primeAccountNumber) {
        this.primeAccountNumber.set(primeAccountNumber);
    }

    public SimpleStringProperty primeAccountNameProperty() {
        return primeAccountName;
    }

    public String getAgressoAccountNumber() {
        return agressoAccountNumber.get();
    }

    public SimpleStringProperty agressoAccountNumberProperty() {
        return agressoAccountNumber;
    }

    public void setAgressoAccountNumber(String agressoAccountNumber) {
        this.agressoAccountNumber.set(agressoAccountNumber);
    }

    public SimpleStringProperty agressoAccountNameProperty() {
        return agressoAccountName;
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public SimpleStringProperty valutaProperty() {
        return valuta;
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public double getAmountInEuro() {
        return amountInEuro.get();
    }

    public SimpleDoubleProperty amountInEuroProperty() {
        return amountInEuro;
    }

    public void setAmountInEuro(double amountInEuro) {
        this.amountInEuro.set(amountInEuro);
    }

    public String getMainCategory() {
        return mainCategory.get();
    }

    public SimpleStringProperty mainCategoryProperty() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory.set(mainCategory);
    }

    public String getSubCategory() {
        return subCategory.get();
    }

    public SimpleStringProperty subCategoryProperty() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory.set(subCategory);
    }

    public String getYear() {
        return year.get();
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getQuarter() {
        return quarter.get();
    }

    public SimpleStringProperty quarterProperty() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter.set(quarter);
    }

    public double getConversionRate() {
        return conversionRate.get();
    }

    public SimpleDoubleProperty conversionRateProperty() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate.set(conversionRate);
    }
}

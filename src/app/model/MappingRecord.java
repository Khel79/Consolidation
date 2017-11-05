package app.model;

import javafx.beans.property.SimpleStringProperty;

public class MappingRecord {

    private SimpleStringProperty accountNumber;
    private SimpleStringProperty accountName;
    private SimpleStringProperty mainCategory;
    private SimpleStringProperty subCategory;

    public MappingRecord(String accountNumber, String accountName, String mainCategory, String subCategory) {
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.accountName = new SimpleStringProperty(accountName);
        this.mainCategory = new SimpleStringProperty(mainCategory);
        this.subCategory = new SimpleStringProperty(subCategory);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public SimpleStringProperty accountNameProperty() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
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
}

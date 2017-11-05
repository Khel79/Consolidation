package app.model;

public class AccountCategoryMapping {

    private String account;
    private String mainCategory;
    private String subCategory;

    public AccountCategoryMapping(String account, String mainCategory, String subCategory) {
        this.account = account;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}

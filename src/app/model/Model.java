package app.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<AgressoRecord> agressoRecordList = new ArrayList<AgressoRecord>();
    private String conversionFileName = "src" + File.separator + "files" + File.separator + "conversionrates.csv";
    private String mainCategoriesFileName = "src" + File.separator + "files" + File.separator + "maincategories.csv";
    private String subcategoriesFileName = "src" + File.separator + "files" + File.separator + "subcategories.csv";
    private String categoryMappingFileName = "src" + File.separator + "files" + File.separator + "categorymapping.csv";

    private List<MappingRecord> mappingTableList = new ArrayList<>();
    private List<String> mainCategoriesList = new ArrayList<>();
    private List<String> subCategoriesList = new ArrayList<>();
    private List<String> categoryMappingList = new ArrayList<>();

    public Model() {
    }

    public double readConversionRateFile(File file, String conversionType) {
        List<String> data = FileManager.readFromFile(conversionFileName);
        double conversionRate = 0; // leaving this at zero will show if the switch failed, because the amount will result in 0
        switch (conversionType) {
            case "Already in EUR":
                conversionRate = 1;
                break;
            case "KES -> EUR":
                conversionRate = Double.parseDouble(data.get(0).split(";")[1]);
                break;
            case "MYR -> EUR":
                conversionRate = Double.parseDouble(data.get(0).split(";")[3]);
                break;
            case "USD -> EUR":
                conversionRate = Double.parseDouble(data.get(0).split(";")[5]);
                break;
        }
        return conversionRate;
    }

    public void readMainCategoryFile() {
        mainCategoriesList = FileManager.readFromFile(mainCategoriesFileName);
        for (int i = 0; i < mainCategoriesList.size(); i++) {
            mainCategoriesList.set(i, mainCategoriesList.get(i).replace(";", ""));
        }
    }

    public void readSubCategoryFile() {
        subCategoriesList = FileManager.readFromFile(subcategoriesFileName);
        for (int i = 0; i < subCategoriesList.size(); i++) {
            subCategoriesList.set(i, subCategoriesList.get(i).replace(";", ""));
        }
    }

    public void readCategoryMappingFile() {
        categoryMappingList = FileManager.readFromFile(categoryMappingFileName);
    }

    public void addMainCategory(String mainCategoryName) {
       mainCategoriesList.add(mainCategoryName);
    }

    public void writeMainCategoriesToFile() {
        FileManager.writeDataToFile(mainCategoriesFileName, mainCategoriesList);
    }

    public void addSubCategory(String mainCategoryName, String subCategoryName) {
        subCategoriesList.add(mainCategoryName + "," + subCategoryName);
    }

    public void writeSubCategoriesToFile() {
        FileManager.writeDataToFile(subcategoriesFileName, subCategoriesList);
    }

    public void addMappingRecord(String group, String accountNumber, String accountName, String mainCategory, String subCategory) {
        MappingRecord mappingRecord = new MappingRecord(group, accountNumber, accountName, mainCategory, subCategory);
        mappingTableList.add(mappingRecord);
    }

    public void createMappingTable() {
        for (int i = 1; i < categoryMappingList.size(); i++) { // i=1 to skip header row
            if (!categoryMappingList.get(i).isEmpty()) {
                String[] temp = categoryMappingList.get(i).split(",");
                addMappingRecord(temp[0], temp[1], temp[2], temp[3], temp[4].replaceAll(";", ""));
            }
        }
    }

    public void writeMappingTableToFile() {
        List<String> mappingData = new ArrayList<>();
        mappingData.add("Group,AccountNumber,AccountName,MainCategory,SubCategory;"); // header-row
        for (MappingRecord record : mappingTableList) {
            String temp = record.getGroup() + "," +
                    record.getAccountNumber() + "," +
                    record.getAccountName() + "," +
                    record.getMainCategory() + "," +
                    record.getSubCategory() + ";";
            mappingData.add(temp);
        }
        FileManager.writeDataToFile(categoryMappingFileName, mappingData);
    }

    public void readAgressoFile(File file, String valuta, String year, String quarter, double conversionRate) {
        System.out.println("Opening this file: " + file.toPath().toString());
        List<String> data = FileManager.readFromFile(file.toPath().toString());
        processAgressoData(data, valuta, year, quarter, conversionRate);
    }

    private void processAgressoData(List<String> data, String valuta, String year, String quarter, double conversionRate) {
        for (int i = 1; i < data.size(); i++) {     // i=1 to skip the header row
            String[] temp = data.get(i).split(";");
            AgressoRecord agressoRecord = new AgressoRecord(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], valuta, year, quarter, conversionRate);
            agressoRecordList.add(agressoRecord);
        }
    }

    public List<AgressoRecord> getAgressoRecordList() {
        return agressoRecordList;
    }

    public List<MappingRecord> getMappingTableList() {
        return mappingTableList;
    }

    public List<String> getMainCategoriesList() {
        return mainCategoriesList;
    }

    public List<String> getSubCategoriesList() {
        return subCategoriesList;
    }

    public List<String> getCategoryMappingList() {
        return categoryMappingList;
    }
}

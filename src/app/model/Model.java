package app.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<AgressoRecord> agressoRecordList = new ArrayList<AgressoRecord>();
    private String conversionFileName = "conversionrates.csv";
    private String mainCategoriesFileName = "maincategories.csv";
    private String subcategoriesFileName = "subcategories.csv";
    private String categoryMappingFileName = "categorymapping.csv";

    public Model() {
    }

    public double readConversionRateFile(File file, String conversionType) {
        System.out.println("Opening this file: " + file.toPath().toString());
        List<String> data = FileManager.readFromFile(file.toPath().toString());
        double conversionRate = 0; // leaving this at zero will show in the results if the switch failed
        switch (conversionType) {
            case "Already in EUR": conversionRate = 1; break;
            case "KES -> EUR": conversionRate = Double.parseDouble(data.get(0).split(";")[1]); break;
            case "MYR -> EUR": conversionRate = Double.parseDouble(data.get(0).split(";")[3]); break;
            case "USD -> EUR": conversionRate = Double.parseDouble(data.get(0).split(";")[5]); break;
        }
        return conversionRate;
    }

    public List<AgressoRecord> readAgressoFile(File file, String valuta, String year, String quarter, double conversionRate) {
        System.out.println("Opening this file: " + file.toPath().toString());
        List<String> data = FileManager.readFromFile(file.toPath().toString());
        processData(data, valuta, year, quarter, conversionRate);
        return agressoRecordList;
    }

    private void processData(List<String> data, String valuta, String year, String quarter, double conversionRate) {
        for (int i = 1; i < data.size(); i++) {     // i=1 to skip the first row with headers
            String[] temp = data.get(i).split(";");

            AgressoRecord agressoRecord = new AgressoRecord(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], valuta, year, quarter, conversionRate);

            agressoRecordList.add(agressoRecord);
        }
    }

    public List<AgressoRecord> getAgressoRecordList() {
        return agressoRecordList;
    }
}


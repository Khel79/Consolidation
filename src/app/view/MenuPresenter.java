package app.view;

import app.model.AgressoRecord;
import app.model.Model;
import app.view.data.DataPresenter;
import app.view.data.DataView;
import app.view.mapping.MappingPresenter;
import app.view.mapping.MappingView;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;

public class MenuPresenter {
    private MenuView menuView;
    private Model model;
    private Stage primaryStage;
    private List<AgressoRecord> agressoRecords;

    private static boolean mappingDataLoaded = false;

    public MenuPresenter(Model model, MenuView menuView, Stage primaryStage) {
        this.menuView = menuView;
        this.model = model;
        this.primaryStage = primaryStage;

        readCategoryDataFromFiles();
        initialiseEventHandling();
    }

    private void initialiseEventHandling() {
        menuView.getOpenAgressoFileButton().setOnAction(e -> openAgressoFileAction(menuView.getValutaConversionRateComboBoxValue()));
        menuView.getShowDataButton().setOnAction(e -> showDataAction());
        menuView.getShowMappingTableButton().setOnAction(e -> showMappingTableAction());
        menuView.getExitButton().setOnAction(e -> exitApplicationAction());
    }

    private void readCategoryDataFromFiles() {
        model.readMainCategoryFile();
        model.readSubCategoryFile();
    }

    private void readMappingDataFromFiles() {
        model.readCategoryMappingFile();
        model.createMappingTable();
    }

    private void openAgressoFileAction(String valuta) {
        File file = menuView.getFileChooser().showOpenDialog(null);
        if (file != null) {
            // double conversionRate = model.readConversionRateFile(file, menuView.getValutaConversionRateComboBoxValue());
            System.out.println("Year Combobox = " + menuView.getYearComboBoxValue());
            System.out.println("Quarter Combobox = " + menuView.getQuarterComboBoxValue());
            System.out.println("Conversion Rate = " + menuView.getConversionRateTextFieldValue());
            model.readAgressoFile(file, valuta, menuView.getYearComboBoxValue(), menuView.getQuarterComboBoxValue(), menuView.getConversionRateTextFieldValue());
        }
    }

    private void showDataAction() {
        DataView dataView = new DataView(FXCollections.observableList(model.getAgressoRecordList()));
        dataView.setPrefSize(1200, 800);
        DataPresenter dataPresenter = new DataPresenter(model, dataView, primaryStage);
        menuView.getScene().setRoot(dataView);
        dataView.getScene().getWindow().sizeToScene();
    }

    private void showMappingTableAction() {
        if (!mappingDataLoaded) {
            readMappingDataFromFiles();
            mappingDataLoaded = true;
        }
        MappingView mappingView = new MappingView(FXCollections.observableList(model.getMappingTableList()));
        mappingView.setPrefSize(1200, 800);
        MappingPresenter mappingPresenter = new MappingPresenter(model, mappingView, primaryStage);
        menuView.getScene().setRoot(mappingView);
        mappingView.getScene().getWindow().sizeToScene();
    }

    private void exitApplicationAction() {
        primaryStage.close();
    }
}

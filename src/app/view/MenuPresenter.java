package app.view;

import app.model.AgressoRecord;
import app.model.Model;
import app.view.data.DataPresenter;
import app.view.data.DataViewNew;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MenuPresenter {
    private MenuView menuView;
    private Model model;
    private Stage primaryStage;
    private List<AgressoRecord> agressoRecords;

    public MenuPresenter(Model model, MenuView menuView, Stage primaryStage) {
        this.menuView = menuView;
        this.model = model;
        this.primaryStage = primaryStage;

        initialiseEventHandling();
    }

    private void initialiseEventHandling() {
        menuView.getOpenAgressoFileButton().setOnAction(e -> openAgressoFileAction(menuView.getValutaConversionRateComboBoxValue()));

        /**
        menuView.getOpenEURfileButton().setOnAction(e -> openAgressoFileAction("EUR"));
        menuView.getOpenKESfileButton().setOnAction(e -> openAgressoFileAction("KES"));
        menuView.getOpenMYRfileButton().setOnAction(e -> openAgressoFileAction("MYR"));
        menuView.getOpenUSDfileButton().setOnAction(e -> openAgressoFileAction("USD"));
        */

        menuView.getShowDataButton().setOnAction(e -> showDataAction());

        menuView.getExitButton().setOnAction(e -> exitApplicationAction());

    }

    private void openAgressoFileAction(String valuta) {
        File file = menuView.getFileChooser().showOpenDialog(null);
        if (file != null) {
            // double conversionRate = model.readConversionRateFile(file, menuView.getValutaConversionRateComboBoxValue());
            System.out.println("Year Combobox = " + menuView.getYearComboBoxValue());
            System.out.println("Quarter Combobox = " + menuView.getQuarterComboBoxValue());
            System.out.println("Conversion Rate = " + menuView.getConversionRateTextFieldValue());
            agressoRecords = model.readAgressoFile(file, valuta, menuView.getYearComboBoxValue(), menuView.getQuarterComboBoxValue(), menuView.getConversionRateTextFieldValue());
        }
    }

    private void showDataAction() {
        DataViewNew dataViewNew = new DataViewNew(FXCollections.observableList(agressoRecords));
        dataViewNew.setPrefSize(1200, 800);
        DataPresenter dataPresenter = new DataPresenter(model, dataViewNew, primaryStage);
        menuView.getScene().setRoot(dataViewNew);
        dataViewNew.getScene().getWindow().sizeToScene();
    }


    private void exitApplicationAction() {
        primaryStage.close();
    }

}
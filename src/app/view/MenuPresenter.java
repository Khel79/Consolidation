package app.view;

import app.model.AgressoRecord;
import app.model.Model;
import app.view.data.DataPresenter;
import app.view.data.DataView;
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

    private void exitApplicationAction() {
        primaryStage.close();
    }
}

package app.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.time.LocalDate;

public class MenuView extends BorderPane {

    private final GridPane centerGridPane = new GridPane();
    private final FileChooser fileChooser = new FileChooser();
    private final String[] valutaConversionRatesChoices = {"Already in EUR", "KES -> EUR", "MYR -> EUR", "USD -> EUR"};
    private final String[] quarterChoices = {"Q1", "Q2", "Q3", "Q4"};
    private final int[] yearChoices = {2016, 2017, 2018, 2019, 2020};

    private HBox topBox = new HBox();
    private HBox bottomBox = new HBox();
    private VBox leftBox = new VBox();
    private VBox rightBox = new VBox();

    private ComboBox yearComboBox = new ComboBox();
    private ComboBox quarterComboBox = new ComboBox();
    private ComboBox valutaConversionComboBox = new ComboBox();
    private Label yearLabel = new Label("Select the year for which you are making an import: ");
    private Label quarterLabel = new Label("Select the quarter for which you are making an import: ");
    private Label valutaConversionLabel = new Label("Select the valuta conversion: ");

    private Label conversionRateLabel = new Label("Enter the conversation rate for your selection: ");
    private TextField textFieldConversionRate = new TextField();

    private Button openAgressoFileButton = new Button("Select an Agresso export file");

    /**
     * private Button openEURfileButton = new Button("Select Agresso export in EUR");
     * private Button openKESfileButton = new Button("Select Agresso export in KES");
     * private Button openMYRfileButton = new Button("Select Agresso export in MYR");
     * private Button openUSDfileButton = new Button("Select Agresso export in USD");
     */

    private Button showDataButton = new Button("Show ALL data");

    private Button exitButton = new Button("Exit");

    public MenuView() {
        this.setTop(topBox);
        this.setBottom(bottomBox);
        this.setLeft(leftBox);
        this.setRight(rightBox);
        this.setCenter(centerGridPane);

        for (int year : yearChoices) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setValue(LocalDate.now().getYear());

        for (String quarter : quarterChoices) {
            quarterComboBox.getItems().add(quarter);
        }
        int month = LocalDate.now().getMonthValue();
        switch (month) {
            case 1:
                quarterComboBox.setValue(quarterChoices[3]);
                break;
            case 4:
                quarterComboBox.setValue(quarterChoices[0]);
                break;
            case 7:
                quarterComboBox.setValue(quarterChoices[1]);
                break;
            case 11:
                quarterComboBox.setValue(quarterChoices[2]);
                break;
        }

        for (String valuta : valutaConversionRatesChoices) {
            valutaConversionComboBox.getItems().add(valuta);
        }
        valutaConversionComboBox.setValue(valutaConversionRatesChoices[0]);

        centerGridPane.setHgap(10);
        centerGridPane.setVgap(10);

        centerGridPane.add(yearLabel, 0, 0);
        centerGridPane.add(quarterLabel, 0, 1);
        centerGridPane.add(valutaConversionLabel, 0, 2);

        centerGridPane.add(yearComboBox, 1, 0);
        centerGridPane.add(quarterComboBox, 1, 1);
        centerGridPane.add(valutaConversionComboBox, 1, 2);

        centerGridPane.add(conversionRateLabel, 0, 3);
        centerGridPane.add(textFieldConversionRate, 1, 3);

        centerGridPane.add(openAgressoFileButton, 0, 4);

        /**
         centerGridPane.add(openEURfileButton, 0, 4);
         centerGridPane.add(openKESfileButton, 0, 5);
         centerGridPane.add(openMYRfileButton, 0, 6);
         centerGridPane.add(openUSDfileButton, 0, 7);
         */

        centerGridPane.add(showDataButton, 0, 9);

        centerGridPane.add(exitButton, 3, 11);
    }

    public String getYearComboBoxValue() {
        return yearComboBox.getValue().toString();
    }

    public String getQuarterComboBoxValue() {
        return quarterComboBox.getValue().toString();
    }

    public String getValutaConversionRateComboBoxValue() {
        if (valutaConversionComboBox.getValue().toString().equals(valutaConversionRatesChoices[0])) {
            return valutaConversionComboBox.getValue().toString().substring(11, 14);
        } else {
            return valutaConversionComboBox.getValue().toString().substring(0, 3);
        }
    }

    public double getConversionRateTextFieldValue() {
        if (textFieldConversionRate.getText().isEmpty()) {
            return 1;
        } else {
            return Double.parseDouble(textFieldConversionRate.getText().replace(",", "."));
        }
    }

    public Button getOpenAgressoFileButton() {
        return openAgressoFileButton;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public Button getShowDataButton() {
        return showDataButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}

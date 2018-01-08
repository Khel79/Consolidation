package app.view.mapping;

import app.model.MappingRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MappingView extends VBox {
    private ObservableList<MappingRecord> data = FXCollections.observableArrayList();

    private TableView<MappingRecord> table = new TableView<>();

    private TableColumn<MappingRecord, String> groupColumn = new TableColumn<>("Group");
    private TableColumn<MappingRecord, String> accountNumberColumn = new TableColumn<>("Account");
    private TableColumn<MappingRecord, String> accountNameColumn = new TableColumn<>("Account Description");
    private TableColumn<MappingRecord, String> mainCategoryColumn = new TableColumn<>("Main Category");
    private TableColumn<MappingRecord, String> subCategoryColumn = new TableColumn<>("Subcategory");

    private List<String> subCategoriesList = new ArrayList<>();

    private Label mainCategoryComboBoxLabel = new Label("Select the main category: ");
    private Label subCategoryComboBoxLabel = new Label("Select the sub category: ");

    private ComboBox<String> groupComboBox = new ComboBox<>();
    private ComboBox<String> subCategoryComboBox = new ComboBox<>();
    private ComboBox<String> mainCategoryComboBox = new ComboBox<>();

    private Button addNewMainCategoryButton = new Button("Add a new main category");
    private Button addNewSubCategoryButton = new Button("Add a new sub category");
    private Button addNewTableMappingButton = new Button("Add a new tablemapping");

    private TextField accountNumberTextField = new TextField();
    private TextField accountNameTextField = new TextField();

    private Label groupComboBoxLabel = new Label("Select the group: ");
    private Label accountNumberTextFieldLabel = new Label("Enter the account number: ");
    private Label accountNameTextFieldLabel = new Label("Enter the account description: ");

    private Label accountNumberTextFieldErrorLabel = new Label();
    private Label accountNameTextFieldErrorLabel = new Label();

    private GridPane gridPane = new GridPane();

    private Button saveButton = new Button("Save the above record");

    private Button goToMainMenuButton = new Button("Main menu");

    public MappingView(ObservableList<MappingRecord> data) {
        // p.getValue() returns the Object instance of the data for a particular TableView row
        groupColumn.setCellValueFactory(p -> p.getValue().groupProperty());
        accountNumberColumn.setCellValueFactory(p -> p.getValue().accountNumberProperty());
        accountNameColumn.setCellValueFactory(p -> p.getValue().accountNameProperty());
        mainCategoryColumn.setCellValueFactory(p -> p.getValue().mainCategoryProperty());
        subCategoryColumn.setCellValueFactory(p -> p.getValue().subCategoryProperty());

        groupColumn.setStyle("-fx-alignment: CENTER;");
        accountNumberColumn.setStyle("-fx-alignment: TOP-RIGHT;");
        accountNameColumn.setStyle("-fx-alignment: CENTER;");
        mainCategoryColumn.setStyle("-fx-alignment: CENTER;");
        subCategoryColumn.setStyle("-fx-alignment: CENTER;");

        table.getColumns().addAll(groupColumn, accountNumberColumn, accountNameColumn, mainCategoryColumn, subCategoryColumn);
        table.setItems(data);
        getChildren().add(table);
        getChildren().add(goToMainMenuButton);

        // Section for creating a new mapping combination
        gridPane.add(new Label("Add a new record"), 0, 0);

        gridPane.add(groupComboBoxLabel, 0, 2);
        gridPane.add(groupComboBox, 1, 2);

        gridPane.add(accountNumberTextFieldLabel, 0, 3);
        gridPane.add(accountNumberTextField, 1, 3);
        gridPane.add(accountNumberTextFieldErrorLabel, 2, 3);

        gridPane.add(accountNameTextFieldLabel, 0, 4);
        gridPane.add(accountNameTextField, 1, 4);
        gridPane.add(accountNameTextFieldErrorLabel, 2, 4);

        gridPane.add(mainCategoryComboBoxLabel, 0, 5);
        gridPane.add(mainCategoryComboBox, 1, 5);

        gridPane.add(subCategoryComboBoxLabel, 0, 6);
        gridPane.add(subCategoryComboBox, 1, 6);

        gridPane.add(saveButton, 1, 7);

        getChildren().add(gridPane);
        setHeight(Double.MAX_VALUE);
    }

    public void initializeValuesForGroupComboBox() {
        groupComboBox.getItems().addAll("B", "R");
    }

    public void initializeValuesForMainCategoryComboBox(List<String> mainCategoryList) {
        for (String category: mainCategoryList) {
            mainCategoryComboBox.getItems().add(category);
        }
        mainCategoryComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                subCategoryComboBox.getItems().clear();
                subCategoryComboBox.setDisable(true);
            } else {
                List<String> subCategories = getSubCategoriesForMainCategory(newValue);
                subCategoryComboBox.getItems().setAll(subCategories);
                subCategoryComboBox.setDisable(false);
            }
        });
    }

    public void initializeValuesForSubCategoryComboBox(List<String> subCategoriesList) {
        subCategoryComboBox.setDisable(true);
        this.subCategoriesList = subCategoriesList;
    }

    private List<String> getSubCategoriesForMainCategory(String mainCategory) {
        List<String> subcategoryList = new ArrayList<>();
        for (String subcategory: subCategoriesList) {
            if (subcategory.split(",")[0].equals(mainCategory)) {
                subcategoryList.add(subcategory.split(",")[1].replace(";", ""));
            }
        }
        return subcategoryList;
    }

    public String getGroupComboBoxValue() {
        return groupComboBox.getValue();
    }
    public String getMainCategoryComboBoxValue() {
        return mainCategoryComboBox.getValue();
    }
    public String getSubCategoryComboBoxValue() {
        return subCategoryComboBox.getValue();
    }


    public Button getAddNewMainCategoryButton() {
        return addNewMainCategoryButton;
    }

    public Button getAddNewSubCategoryButton() {
        return addNewSubCategoryButton;
    }

    public Button getAddNewTableMappingButton() {
        return addNewTableMappingButton;
    }

    public Button getGoToMainMenuButton() {
        return goToMainMenuButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public String getAccountNumberTextFieldValue() {
        return accountNumberTextField.getText();
    }

    public String getAccountNameTextFieldValue() {
        return accountNameTextField.getText();
    }

}

package app.view.mapping;

import app.model.MappingRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

    private List<String> mainCategoriesList = new ArrayList<>();
    private List<String> subCategoriesList = new ArrayList<>();

    private Label mainCategoryComboBoxLabel = new Label("Select the main category: ");
    private Label subCategoryComboBoxLabel = new Label("Select the sub category: ");

    private ComboBox<String> groupComboBox = new ComboBox<>();
    private ComboBox<String> subCategoryComboBox = new ComboBox<>();
    private ComboBox<String> mainCategoryComboBox = new ComboBox<>();

    private TextField accountNumberTextField = new TextField();
    private TextField accountNameTextField = new TextField();

    private Label groupComboBoxLabel = new Label("Select the group: ");
    private Label accountNumberTextFieldLabel = new Label("Enter the account number: ");
    private Label accountNameTextFieldLabel = new Label("Enter the account description: ");

    private Label accountNumberTextFieldErrorLabel = new Label();
    private Label accountNameTextFieldErrorLabel = new Label();

    private GridPane gridPane = new GridPane();

    private Button saveRecordButton = new Button("Save the above record");

    private Label newMainCategoryTextFieldLabel = new Label("Enter the name:");
    private TextField newMainCategoryTextField = new TextField();
    private Button saveNewMainCategoryButton = new Button("Add new main category");

    private Label newSubCategoryTextFieldLabel = new Label("Enter the name:");
    private TextField newSubCategoryTextField = new TextField();
    private Label newSubCategoryMainCategorySelectionLabel = new Label("Select the related main category");
    private ComboBox<String> newSubCategoryMainCategorySelectionComboBox = new ComboBox<>();
    private Button saveNewSubCategoryButton = new Button("Add new sub category");

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

        // Section for creating a new record/mapping combination
        gridPane.setHgap(10);
        gridPane.setVgap(10);
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

        gridPane.add(saveRecordButton, 1, 7);

        // Section for creating a new main category
        gridPane.add(new Label("Add a new main category"), 3, 0);
        gridPane.add(newMainCategoryTextFieldLabel, 3, 2);
        gridPane.add(newMainCategoryTextField, 4, 2);
        gridPane.add(saveNewMainCategoryButton, 4, 4);

        // Section for creating a new subcategory
        gridPane.add(new Label("Add a new sub category"), 6, 0);
        gridPane.add(newSubCategoryTextFieldLabel, 6, 2);
        gridPane.add(newSubCategoryTextField, 7, 2);
        gridPane.add(newSubCategoryMainCategorySelectionLabel, 6, 3);
        gridPane.add(newSubCategoryMainCategorySelectionComboBox, 7, 3);
        gridPane.add(saveNewSubCategoryButton, 7, 4);

        getChildren().add(gridPane);
        setMargin(gridPane, new Insets(10, 10, 10, 10));
        setHeight(Double.MAX_VALUE);
    }

    // TODO: 1. Show notification after saving a value that the save was successful
    // TODO: 2. Clear the textfield(s) and or combobox selection after a successful save
    // TODO: 3. Check for duplicate subcategory + maincategory combination creation on save
    // TODO: 4. Refactor List<String> to Map<String,String> (prevents duplicates)

    public void initializeValuesForGroupComboBox() {
        groupComboBox.getItems().addAll("B", "R");
    }

    public void initializeValuesForMainCategoryComboBox(List<String> mainCategoryList) {
        for (String category : mainCategoryList) {
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

    public void initializeValuesForNewSubCategoryMainCategoryComboBox(List<String> mainCategoriesList) {
        this.mainCategoriesList = mainCategoriesList;
        for (String category : mainCategoriesList) {
            newSubCategoryMainCategorySelectionComboBox.getItems().add(category);
        }
    }

    public void initializeValuesForSubCategoryComboBox(List<String> subCategoriesList) {
        subCategoryComboBox.setDisable(true);
        this.subCategoriesList = subCategoriesList;
    }

    public void reloadMainCategoryList(List<String> mainCategoriesList) {
        this.mainCategoriesList = mainCategoriesList;
        initializeValuesForMainCategoryComboBox(mainCategoriesList);
        initializeValuesForNewSubCategoryMainCategoryComboBox(mainCategoriesList);
    }

    public void reloadSubCategoryList(List<String> subCategoriesList) {
        this.subCategoriesList = subCategoriesList;
        initializeValuesForSubCategoryComboBox(subCategoriesList);
    }

    private List<String> getSubCategoriesForMainCategory(String mainCategory) {
        List<String> subcategoryList = new ArrayList<>();
        for (String subcategory : subCategoriesList) {
            if (subcategory.split(",")[0].equals(mainCategory)) {
                subcategoryList.add(subcategory.split(",")[1].replace(";", ""));
            }
        }
        return subcategoryList;
    }

    public boolean checkSaveRecordValues() {
        boolean save = true;
        if (groupComboBox.getSelectionModel().isEmpty() ||
                mainCategoryComboBox.getSelectionModel().isEmpty() ||
                subCategoryComboBox.getSelectionModel().isEmpty() ||
                accountNumberTextField.getText().isEmpty() ||
                accountNameTextField.getText().isEmpty()) {
            save = false;
            showEmptyValuesAlert();
        }
        return save;
    }

    public boolean checkSaveNewMainCategoryValues() {
        boolean save = true;
        if (newMainCategoryTextField.getText().isEmpty()) {
            save = false;
            showEmptyValuesAlert();
        } else {
            for (String mainCategory : mainCategoriesList) {
                if (mainCategory.equals(newMainCategoryTextField.getText())) {
                    save = false;
                    showValueAlreadyExistsAlert();
                }
            }
        }
        return save;
    }

    public boolean checkSaveNewSubCategoryRecordValues() {
        boolean save = true;
        if (newSubCategoryMainCategorySelectionComboBox.getSelectionModel().isEmpty() ||
                newSubCategoryTextField.getText().isEmpty()) {
            save = false;
            showEmptyValuesAlert();
        } else {
            for (String subCategory : subCategoriesList) {
                if (subCategory.equals(newSubCategoryTextField.getText())) {
                    save = false;
                    showValueAlreadyExistsAlert();
                }
            }
        }
        return save;
    }

    private void showValueAlreadyExistsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Value already exists ERROR");
        alert.setHeaderText("ERROR: Value already exists");
        alert.setContentText("You are trying to save a record, but the entered value already exists. " +
                "Use the mapping option to link an existing main category to an existing sub category.");

        alert.showAndWait();
    }

    private void showEmptyValuesAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty value Warning");
        alert.setHeaderText("Warning: Empty values");
        alert.setContentText("You are trying to save a record, but not all the values are filled out. Please double-check that all fields have a value.");

        alert.showAndWait();
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

    public TextField getNewMainCategoryTextField() {
        return newMainCategoryTextField;
    }

    public TextField getNewSubCategoryTextField() {
        return newSubCategoryTextField;
    }

    public ComboBox<String> getNewSubCategoryMainCategorySelectionComboBox() {
        return newSubCategoryMainCategorySelectionComboBox;
    }

    public Button getGoToMainMenuButton() {
        return goToMainMenuButton;
    }

    public Button getSaveRecordButton() {
        return saveRecordButton;
    }

    public Button getSaveNewMainCategoryButton() {
        return saveNewMainCategoryButton;
    }

    public Button getSaveNewSubCategoryButton() {
        return saveNewSubCategoryButton;
    }

    public String getAccountNumberTextFieldValue() {
        return accountNumberTextField.getText();
    }

    public String getAccountNameTextFieldValue() {
        return accountNameTextField.getText();
    }

}

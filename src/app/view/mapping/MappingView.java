package app.view.mapping;

import app.model.MappingRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

@SuppressWarnings("FieldCanBeLocal")
public class MappingView extends VBox {
    private ObservableList<MappingRecord> data = FXCollections.observableArrayList();

    private TableView<MappingRecord> table = new TableView<MappingRecord>();

    private TableColumn<MappingRecord, String> groupColumn = new TableColumn<MappingRecord, String>("Group");
    private TableColumn<MappingRecord, String> accountNumberColumn = new TableColumn<MappingRecord, String>("Account");
    private TableColumn<MappingRecord, String> accountNameColumn = new TableColumn<MappingRecord, String>("Account Description");
    private TableColumn<MappingRecord, String> mainCategoryColumn = new TableColumn<MappingRecord, String>("Main Category");
    private TableColumn<MappingRecord, String> subCategoryColumn = new TableColumn<MappingRecord, String>("Subcategory");

    private Label mainCategoryComboBoxLabel = new Label("Select the main category");
    private Label subCategoryComboBoxLabel = new Label("Select the sub category");

    private ComboBox mainCategoryComboBox = new ComboBox();
    private ComboBox subCategoryComboBox = new ComboBox();
    private ComboBox groupComboBox = new ComboBox();

    private Button addNewMainCategoryButton = new Button("Add a new main category");
    private Button addNewSubCategoryButton = new Button("Add a new sub category");
    private Button addNewTableMappingButton = new Button("Add a new tablemapping");

    private TextField groupTextField = new TextField();
    private TextField accountNumberTextField = new TextField();
    private TextField accountNameTextField = new TextField();

    private Label groupTextFieldLabel = new Label();
    private Label accountNumberTextFieldLabel = new Label();
    private Label accountNameTextFieldLabel = new Label();

    private Label groupTextFieldErrorLabel = new Label();
    private Label accountNumberTextFieldErrorLabel = new Label();
    private Label accountNameTextFieldErrorLabel = new Label();

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
        setHeight(Double.MAX_VALUE);
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

    public TextField getGroupTextField() {
        return groupTextField;
    }

    public TextField getAccountNumberTextField() {
        return accountNumberTextField;
    }

    public TextField getAccountNameTextField() {
        return accountNameTextField;
    }

}

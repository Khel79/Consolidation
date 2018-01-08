package app.view.mapping;

import app.model.Model;
import app.view.MenuPresenter;
import app.view.MenuView;
import javafx.stage.Stage;

public class MappingPresenter {
    private MappingView mappingView;
    private Model model;
    private Stage primaryStage;

    public MappingPresenter(Model model, MappingView mappingView, Stage primaryStage) {
        this.mappingView = mappingView;
        this.model = model;
        this.primaryStage = primaryStage;

        initialiseEventHandling();
    }

    private void initialiseEventHandling() {
        //mappingView.getAddNewMainCategoryButton().setOnAction(e -> addMainCategoryAction());
        //mappingView.getAddNewSubCategoryButton().setOnAction(e -> addSubCategoryAction());
        //mappingView.getAddNewTableMappingButton().setOnAction(e -> addMappingAction());
        mappingView.getGoToMainMenuButton().setOnAction(e -> showMainMenuAction());
        mappingView.initializeValuesForGroupComboBox();
        mappingView.initializeValuesForMainCategoryComboBox(model.getMainCategoriesList());
        mappingView.initializeValuesForSubCategoryComboBox(model.getSubCategoriesList());
        mappingView.getSaveButton().setOnAction(e -> saveRecordAction());
    }

    private void showMainMenuAction() {
        MenuView menuView = new MenuView();
        menuView.setPrefSize(1200, 800);
        MenuPresenter menuPresenter = new MenuPresenter(model, menuView, primaryStage);
        mappingView.getScene().setRoot(menuView);
        menuView.getScene().getWindow().sizeToScene();
    }

    private void saveRecordAction() {
        model.addMappingRecord(mappingView.getGroupComboBoxValue(), mappingView.getAccountNumberTextFieldValue(),mappingView.getAccountNameTextFieldValue(),mappingView.getMainCategoryComboBoxValue(), mappingView.getSubCategoryComboBoxValue());
        model.writeMappingTableToFile();
    }
}

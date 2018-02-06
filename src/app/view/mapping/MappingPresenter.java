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
        mappingView.initializeValuesForNewSubCategoryMainCategoryComboBox(model.getMainCategoriesList());
        mappingView.getSaveRecordButton().setOnAction(e -> saveRecordAction(mappingView.checkSaveRecordValues()));
        mappingView.getSaveNewMainCategoryButton().setOnAction(e -> saveNewMainCategoryAction(mappingView.checkSaveNewMainCategoryValues()));
        mappingView.getSaveNewSubCategoryButton().setOnAction(e -> saveNewSubCategoryAction(mappingView.checkSaveNewSubCategoryRecordValues()));
    }

    private void showMainMenuAction() {
        MenuView menuView = new MenuView();
        menuView.setPrefSize(1200, 800);
        MenuPresenter menuPresenter = new MenuPresenter(model, menuView, primaryStage);
        mappingView.getScene().setRoot(menuView);
        menuView.getScene().getWindow().sizeToScene();
    }

    private void saveRecordAction(Boolean allValuesEntered) {
        if (allValuesEntered) {
            model.addMappingRecord(mappingView.getGroupComboBoxValue(), mappingView.getAccountNumberTextFieldValue(), mappingView.getAccountNameTextFieldValue(), mappingView.getMainCategoryComboBoxValue(), mappingView.getSubCategoryComboBoxValue());
            model.writeMappingTableToFile();
            mappingView.reloadMainCategoryList(model.getMainCategoriesList());
            mappingView.reloadSubCategoryList(model.getSubCategoriesList());
        }
    }

    private void saveNewMainCategoryAction(Boolean allValuesEntered) {
        if (allValuesEntered) {
            model.addMainCategory(mappingView.getNewMainCategoryTextField().getText());
            model.writeMainCategoriesToFile();
            mappingView.reloadMainCategoryList(model.getMainCategoriesList());
        }
    }

    private void saveNewSubCategoryAction(Boolean allValuesEntered) {
        if (allValuesEntered) {
            model.addSubCategory(mappingView.getNewSubCategoryMainCategorySelectionComboBox().getValue(), mappingView.getNewSubCategoryTextField().getText());
            model.writeSubCategoriesToFile();
            mappingView.reloadSubCategoryList(model.getSubCategoriesList());
        }
    }
}

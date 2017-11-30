package app.view.data;

import app.model.Model;
import app.view.MenuPresenter;
import app.view.MenuView;
import javafx.stage.Stage;

public class DataPresenter {
    private DataView dataView;
    private Model model;
    private Stage primaryStage;

    public DataPresenter(Model model, DataView dataView, Stage primaryStage) {
        this.dataView = dataView;
        this.model = model;
        this.primaryStage = primaryStage;

        initialiseEventHandling();
    }

    private void initialiseEventHandling() {
        dataView.getGoToMainMenuButton().setOnAction(e -> showMainMenuAction());
    }

    private void showMainMenuAction() {
        MenuView menuView = new MenuView();
        menuView.setPrefSize(1200, 800);
        MenuPresenter menuPresenter = new MenuPresenter(model, menuView, primaryStage);
        dataView.getScene().setRoot(menuView);
        menuView.getScene().getWindow().sizeToScene();
    }
}

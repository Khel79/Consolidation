package app;

import app.model.Model;
import app.view.MenuPresenter;
import app.view.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(model, menuView, primaryStage);

        Scene scene = new Scene(menuView);
        menuView.setPrefSize(1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agresso Consolidation Tool");
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package app.view.data;


import app.model.Model;
import javafx.stage.Stage;

public class DataPresenter {
    private DataView dataView;

    private Model model;
    private Stage primaryStage;

    public DataPresenter(Model model, DataView dataView, Stage primaryStage) {
        this.dataView = dataView;
        this.model = model;
        this.primaryStage = primaryStage;

        //initialiseEventHandling();
    }
}

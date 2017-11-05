package app.view.data;


import app.model.Model;
import javafx.stage.Stage;

public class DataPresenter {
    private DataViewNew dataViewNew;

    private Model model;
    private Stage primaryStage;

    public DataPresenter(Model model, DataViewNew dataViewNew, Stage primaryStage) {
        this.dataViewNew = dataViewNew;
        this.model = model;
        this.primaryStage = primaryStage;

        //initialiseEventHandling();
    }
}

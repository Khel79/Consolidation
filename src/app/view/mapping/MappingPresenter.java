package app.view.mapping;

import app.model.Model;
import javafx.stage.Stage;

public class MappingPresenter {
    private MappingView mappingView;
    private Model model;
    private Stage primaryStage;

    public MappingPresenter(Model model, MappingView mappingView, Stage primaryStage) {
        this.mappingView = mappingView;
        this.model = model;
        this.primaryStage = primaryStage;

        //initialiseEventHandling();
    }
}

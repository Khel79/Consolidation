package app.view.data;

import app.model.AgressoRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class DataViewBackup extends VBox {
    private ObservableList<AgressoRecord> data = FXCollections.observableArrayList();

    private TableView<AgressoRecord> table = new TableView<AgressoRecord>();

    private TableColumn groupColumn = new TableColumn("Group");
    //private TableColumn primeAccountNumberColumn = new TableColumn("Prime Account");
    //private TableColumn primeAccountNameColumn = new TableColumn("Prime Account Description");
    private TableColumn agressoNumberColumn = new TableColumn("Account");
    private TableColumn agressoNameColumn = new TableColumn("Account Description");
    private TableColumn amountColumn = new TableColumn("Amount");
    private TableColumn valutaColumn = new TableColumn("Valuta");
    private TableColumn countryColumn = new TableColumn("Country");

    public DataViewBackup(ObservableList<AgressoRecord> data) {
        this.data = data;

        groupColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("group"));
        //primeAccountNumberColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,Double>("primeAccountNumber"));
        //primeAccountNameColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("primeAccountName"));
        agressoNumberColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,Double>("agressoAccountNumber"));
        agressoNameColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("agressoAccountName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("amount"));
        valutaColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("valuta"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<AgressoRecord,String>("country"));





        table.getColumns().addAll(groupColumn, agressoNumberColumn, agressoNameColumn, amountColumn, valutaColumn, countryColumn);
        table.setItems(data);
        this.getChildren().add(table);
        this.setHeight(Double.MAX_VALUE);
    }
}

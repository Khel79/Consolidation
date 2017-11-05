package app.view.mapping;

import app.model.AgressoRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;

@SuppressWarnings("FieldCanBeLocal")
public class MappingView extends VBox {
    private ObservableList<AgressoRecord> data = FXCollections.observableArrayList();

    private TableView<AgressoRecord> table = new TableView<AgressoRecord>();

    private TableColumn<AgressoRecord, String> groupColumn = new TableColumn<AgressoRecord, String>("Group");
    private TableColumn<AgressoRecord, String> agressoNumberColumn = new TableColumn<AgressoRecord, String>("Account");
    private TableColumn<AgressoRecord, String> agressoNameColumn = new TableColumn<AgressoRecord, String>("Account Description");
    private TableColumn<AgressoRecord, Number> amountColumn = new TableColumn<AgressoRecord, Number>("Amount");
    private TableColumn<AgressoRecord, String> valutaColumn = new TableColumn<AgressoRecord, String>("Valuta");
    private TableColumn<AgressoRecord, String> countryColumn = new TableColumn<AgressoRecord, String>("Country");
    private TableColumn<AgressoRecord, Number> amountInEuroColumn = new TableColumn<AgressoRecord, Number>("Amount in EUR");
    private TableColumn<AgressoRecord, String> yearColumn = new TableColumn<AgressoRecord, String>("Year");
    private TableColumn<AgressoRecord, String> quarterColumn = new TableColumn<AgressoRecord, String>("Quarter");

    public MappingView(ObservableList<AgressoRecord> data) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        TableView<AgressoRecord> tableView = new TableView<AgressoRecord>(data);

        // p.getValue() returns the Object instance of the data for a particular TableView row
        groupColumn.setCellValueFactory(p -> p.getValue().groupProperty());
        agressoNumberColumn.setCellValueFactory(p -> p.getValue().agressoAccountNumberProperty());
        agressoNameColumn.setCellValueFactory(p -> p.getValue().agressoAccountNameProperty());
        amountColumn.setCellValueFactory(p -> p.getValue().amountProperty());
        valutaColumn.setCellValueFactory(p -> p.getValue().valutaProperty());
        countryColumn.setCellValueFactory(p -> p.getValue().countryProperty());
        amountInEuroColumn.setCellValueFactory(p -> p.getValue().amountInEuroProperty());
        yearColumn.setCellValueFactory(p -> p.getValue().yearProperty());
        quarterColumn.setCellValueFactory(p -> p.getValue().quarterProperty());

        groupColumn.setStyle("-fx-alignment: CENTER;");
        agressoNumberColumn.setStyle("-fx-alignment: TOP-RIGHT;");
        valutaColumn.setStyle("-fx-alignment: CENTER;");
        countryColumn.setStyle("-fx-alignment: CENTER;");
        yearColumn.setStyle("-fx-alignment: CENTER;");
        quarterColumn.setStyle("-fx-alignment: CENTER;");

        amountColumn.setCellFactory(col ->
                new TableCell<AgressoRecord, Number>() {
                    @Override
                    public void updateItem(Number amount, boolean empty) {
                        super.updateItem(amount, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(decimalFormat.format(amount.doubleValue()));
                            setStyle("-fx-alignment: top-right;");

                        }
                    }
                });

        amountInEuroColumn.setCellFactory(col ->
                new TableCell<AgressoRecord, Number>() {
                    @Override
                    public void updateItem(Number amountInEuro, boolean empty) {
                        super.updateItem(amountInEuro, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(decimalFormat.format(amountInEuro.doubleValue()));
                            setStyle("-fx-alignment: top-right;");
                        }
                    }
                });

        table.getColumns().addAll(groupColumn, agressoNumberColumn, agressoNameColumn, amountColumn, valutaColumn,
                countryColumn, amountInEuroColumn, yearColumn, quarterColumn);
        table.setItems(data);
        getChildren().add(table);
        setHeight(Double.MAX_VALUE);
    }
}

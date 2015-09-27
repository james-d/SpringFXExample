package springfx.table;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import springfx.model.Model;
import springfx.model.Contact;

public class TablePresenter implements Initializable {

    @FXML
    private TableView<Contact> table ;
    @FXML
    private TableColumn<Contact, String> firstNameColumn ;
    @FXML
    private TableColumn<Contact, String> lastNameColumn ;
    @FXML
    private TableColumn<Contact, String> emailColumn ;
        
    private final Model model ;
        
    public TablePresenter(Model model) {
        this.model = model ;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        FilteredList<Contact> filteredList = new FilteredList<>(model.getContactList());
        table.setItems(filteredList);
        
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(
                () -> model::passesFilter,
                model.searchFilterProperty()));
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                model.setCurrentContact(newSelection));
        
        model.currentContactProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson == null) { 
                table.getSelectionModel().clearSelection();
            } else {
                table.getSelectionModel().select(newPerson);
            }
        });
        
        initializeColumn(firstNameColumn, Contact::firstNameProperty);
        initializeColumn(lastNameColumn, Contact::lastNameProperty);
        initializeColumn(emailColumn, Contact::emailProperty);
        
    }

    private <S,T> void initializeColumn(TableColumn<S,T> column, Function<S, ObservableValue<T>> prop) {
        column.setCellValueFactory(cellData -> prop.apply(cellData.getValue()));
    }

}

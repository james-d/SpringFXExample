package springfx.model;

import java.util.List;
import java.util.concurrent.Executor;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import springfx.data.DataAccessor;

public class ModelImpl extends AbstractModel {
    
    private DataAccessor dataAccessor ;
        
    @Autowired
    public void setDataAccessor(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor ;
    }
    
    private Executor exec ;
    
    @Autowired
    public void setExecutor(Executor exec) {
        this.exec = exec ;
    }
    
    private final ObservableList<Contact> contactList  ;
    private final FilteredList<Contact> filteredList ;
    
    private final ObjectProperty<Contact> currentContact = new SimpleObjectProperty<>();
    private final StringProperty searchFilter = new SimpleStringProperty("");
        
    public ModelImpl() {
        contactList = FXCollections.observableArrayList(c -> 
            new Observable[] {c.firstNameProperty(), c.lastNameProperty()});
        filteredList = new FilteredList<>(contactList, p -> true);
        
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> 
            c -> {
                String filterLC = searchFilter.get().toLowerCase();
                return c.getFirstName().toLowerCase().startsWith(filterLC) 
                        || c.getLastName().toLowerCase().startsWith(filterLC);
            }, searchFilter));
    }
    
    @PostConstruct
    public void init() {
        Task<List<Contact>> loadTask = new Task<List<Contact>>() {
            @Override
            public List<Contact> call() {
                return dataAccessor.loadData() ;
            }
        };
        loadTask.setOnSucceeded(e -> contactList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contactList ;
    }


    @Override
    public ObjectProperty<Contact> currentContactProperty() {
        return currentContact ;
    }
    
    @Override
    public StringProperty searchFilterProperty() {
        return searchFilter ;
    }
    
    @Override
    public boolean passesFilter(Contact contact) {
        String filterLC = searchFilter.get().trim().toLowerCase();
        return contact.getFirstName().toLowerCase().startsWith(filterLC)
                || contact.getLastName().toLowerCase().startsWith(filterLC) ;
    }


    @Override
    public void addContact(Contact person) {
        contactList.add(person);
    }

    @Override
    public void removeContact(Contact person) {
        contactList.remove(person);
    }


    

}

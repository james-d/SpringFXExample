package springfx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public interface Model {
    
    /** 
     * 
     * @return The list of all {@code Contact}s in the model.
     */
    public ObservableList<Contact> getContactList();

    public void addContact(Contact contact) ;
    public void removeContact(Contact contact);
    
    public ObjectProperty<Contact> currentContactProperty();
    public Contact getCurrentContact() ;
    public void setCurrentContact(Contact contact) ;
    
    public StringProperty searchFilterProperty() ;
    public String getSearchFilter() ;
    public void setSearchFilter(String filter) ;
    
    public boolean passesFilter(Contact contact);
}

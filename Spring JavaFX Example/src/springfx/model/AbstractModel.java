package springfx.model;

public abstract class AbstractModel implements Model {

    @Override
    public final Contact getCurrentContact() {
        return currentContactProperty().get();
    }
    
    @Override
    public final void setCurrentContact(Contact contact) {
        currentContactProperty().set(contact);
    }
    
    @Override
    public final String getSearchFilter() {
        return searchFilterProperty().get();
    }
    
    @Override
    public final void setSearchFilter(String searchFilter) {
        searchFilterProperty().set(searchFilter);
    }
}

package springfx.data;

import java.util.List;

import springfx.model.Contact;

public interface DataAccessor extends AutoCloseable {
    public List<Contact> loadData() ;
    public void saveData(List<Contact> data);
}

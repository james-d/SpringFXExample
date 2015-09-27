package springfx.data;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import springfx.model.Contact;

public class MockDataAccessor implements DataAccessor {
    
    private static final Logger logger = Logger.getLogger(MockDataAccessor.class.getSimpleName());

    // A real data accessor would retrieve data from some persistent
    // store, e.g. via JDBC or JPA, or remotely from a web service, etc.
    // This is just for testing the front end, and hard-codes a few test
    // values.
    
    @Override
    public List<Contact> loadData() {
        return Arrays.asList(
            new Contact("Jacob", "Smith", "jacob.smith@example.com"),
            new Contact("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Contact("Ethan", "Williams", "ethan.williams@example.com"),
            new Contact("Emma", "Jones", "emma.jones@example.com"),
            new Contact("Michael", "Brown", "michael.brown@example.com")
        ) ;
    }

    @Override
    public void saveData(List<Contact> data) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @Override
    public void close() {
        logger.info("Closing data accessor");
    }

}

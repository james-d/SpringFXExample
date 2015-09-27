package springfx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    
    public Contact(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
    
    public Contact() {
        this("", "", "");
    }

    public final StringProperty firstNameProperty() {
        return this.firstName;
    }

    public final String getFirstName() {
        return this.firstNameProperty().get();
    }

    public final void setFirstName(final String firstName) {
        this.firstNameProperty().set(firstName);
    }

    public final StringProperty lastNameProperty() {
        return this.lastName;
    }

    public final String getLastName() {
        return this.lastNameProperty().get();
    }

    public final void setLastName(final String lastName) {
        this.lastNameProperty().set(lastName);
    }

    public final StringProperty emailProperty() {
        return this.email;
    }

    public final String getEmail() {
        return this.emailProperty().get();
    }

    public final void setEmail(final String email) {
        this.emailProperty().set(email);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%s)", getFirstName(), getLastName(), getEmail());
    }
}

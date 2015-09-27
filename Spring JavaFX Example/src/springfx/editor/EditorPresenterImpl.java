package springfx.editor;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import springfx.model.Model;
import springfx.model.Contact;

public class EditorPresenterImpl implements EditorPresenter {
    
    private final Model model ;
    
    @FXML
    private TextField firstNameTextField ;
    @FXML
    private TextField lastNameTextField ;
    @FXML
    private TextField emailTextField ;
    
    @FXML
    private Button addUpdateButton ;
    
    private BooleanBinding updateDisabled ;
            
    public EditorPresenterImpl(Model model) {
        this.model = model ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        updateDisabled = Bindings.createBooleanBinding(() -> {
            if (model.getCurrentContact() == null) {
                return firstNameTextField.getText().isEmpty()
                    || lastNameTextField.getText().isEmpty()
                    || emailTextField.getText().isEmpty() ;
            } else {
                return firstNameTextField.getText().equals(model.getCurrentContact().getFirstName())
                    && lastNameTextField.getText().equals(model.getCurrentContact().getLastName())
                    && emailTextField.getText().equals(model.getCurrentContact().getEmail()) ;
            }
        }, 
        firstNameTextField.textProperty(), lastNameTextField.textProperty(),
        emailTextField.textProperty(), model.currentContactProperty());
        
                
        addUpdateButton.textProperty().bind(
                Bindings.when(model.currentContactProperty().isNull())
                        .then("Add")
                        .otherwise("Update"));
        
        addUpdateButton.disableProperty().bind(updateDisabled);
        
        model.currentContactProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson == null) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                emailTextField.setText("");
            } else {
                firstNameTextField.setText(newPerson.getFirstName());
                lastNameTextField.setText(newPerson.getLastName());
                emailTextField.setText(newPerson.getEmail());
            }
        });
        
    }
    
    @FXML
    public void addUpdate() {
        
        if (updateDisabled.get()) {
            return ; 
        }
        
        if (model.getCurrentContact() == null) {
            Contact contact = new Contact(
                    firstNameTextField.getText(), 
                    lastNameTextField.getText(), 
                    emailTextField.getText());
            model.addContact(contact);
            model.setCurrentContact(contact);
        } else {
            model.getCurrentContact().setFirstName(firstNameTextField.getText());
            model.getCurrentContact().setLastName(lastNameTextField.getText());
            model.getCurrentContact().setEmail(emailTextField.getText());            
        }
    }

}

package springfx.controls;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import springfx.model.Model;

public class ControlsPresenter implements Initializable {

    private final Model model ;
    private final HostServices hostServices ;
    
    @FXML
    private Button deleteButton ;
    @FXML
    private Button emailButton ;
    
    public ControlsPresenter(Model model, HostServices hostServices) {
        this.model = model ;
        this.hostServices = hostServices ;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.disableProperty()
            .bind(model.currentContactProperty().isNull());
        emailButton.disableProperty()
            .bind(model.currentContactProperty().isNull());
    }  

    @FXML
    public void newContact() {
        model.setCurrentContact(null);
    }
    
    @FXML
    public void delete() {
        if (model.getCurrentContact() != null) {
            model.removeContact(model.getCurrentContact());
            model.setCurrentContact(null);
        }
    }
    
    @FXML
    public void mail() {
        if (model.getCurrentContact() != null) {
            try {
                URI uri = new URI("mailto", model.getCurrentContact().getEmail(), null);
                hostServices.showDocument(uri.toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

}

package springfx.search;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import springfx.model.Model;

public class SearchPresenter implements Initializable{
    
    @FXML
    private TextField searchField ;
    
    private final Model model ;
    
    public SearchPresenter(Model model) {
        this.model = model ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        searchField.textProperty().addListener((obs, oldValue, newValue) -> 
//            model.setSearchFilter(newValue));
        searchField.textProperty().bindBidirectional(model.searchFilterProperty());
    }
    
}

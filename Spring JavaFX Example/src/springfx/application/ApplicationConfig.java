package springfx.application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.application.HostServices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import springfx.controls.ControlsPresenter;
import springfx.data.DataAccessor;
import springfx.data.MockDataAccessor;
import springfx.editor.EditorPresenter;
import springfx.editor.EditorPresenterImpl;
import springfx.model.Model;
import springfx.model.ModelImpl;
import springfx.search.SearchPresenter;
import springfx.table.TablePresenter;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public DataAccessor dataAccessor() {
        return new MockDataAccessor();
    }
    
    @Bean
    public Executor executor() {
        return Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
    }
    
    @Bean
    public Model model() {
        return new ModelImpl();
    }
    
    @Bean
    @Scope("prototype")
    public EditorPresenter editorPresenter(Model model) {
        return new EditorPresenterImpl(model);
    }
    
    @Bean
    @Scope("prototype")
    public TablePresenter tablePresenter(Model model) {
        return new TablePresenter(model);
    }
    
    @Bean
    @Scope("prototype")
    public ControlsPresenter controlsPresenter(Model model, HostServices hostServices) {
        return new ControlsPresenter(model, hostServices);
    }
    
    @Bean
    @Scope("prototype")
    public SearchPresenter searchPresenter(Model model) {
        return new SearchPresenter(model);
    }
}

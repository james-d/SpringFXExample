package springfx.application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springfx.fxml.SpringFXMLLoader;

public class Main extends Application {

    private AnnotationConfigApplicationContext applicationContext;
    
    @Override
    public void init() throws Exception {
        super.init();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        // The following only works because all beans that rely on hostServices are created on demand
        // (in this case, because they have prototype scope; it would also be true for lazy-instantiated
        // singleton-scoped beans). If non-lazy singleton-scoped beans had a dependency on hostServices,
        // we would need to register hostServices before loading the applicationContext. A technique
        // for this is outlined in initializeApplicationContext() below
        applicationContext.getBeanFactory().registerSingleton("hostServices", getHostServices());        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {        

        // Can do
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Application.fxml"));
//        loader.setControllerFactory(applicationContext::getBean);
//        Parent root = loader.load();
        
        Parent root = SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getResource("Application.fxml"))
                .load();
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Initializes the application context, providing hostServices as a singleton bean before loading the
    // beans defined in ApplicationConfig. This technique is needed if any non-lazy singleton beans have a 
    // dependency on hostServices.
    
//    private void initializeApplicationContext() {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        beanFactory.registerSingleton("hostServices", getHostServices());
//        applicationContext = new AnnotationConfigApplicationContext(beanFactory);
//        applicationContext.register(ApplicationConfig.class);
//        applicationContext.refresh();
//    }
    
    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

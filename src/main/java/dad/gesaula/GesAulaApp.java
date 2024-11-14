package dad.gesaula;

import dad.gesaula.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {

    private final RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GesAula");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.getIcons().add(new Image("/images/app-icon-64x64.png"));
        primaryStage.show();
    }
}

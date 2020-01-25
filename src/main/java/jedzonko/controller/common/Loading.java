package jedzonko.controller.common;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Loading extends Preloader {
    private static Stage mainStage;
    static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


    @Override
    public void start(Stage stage) throws Exception {
        Parent loadingView = FXMLLoader.load(getClass().getResource("/View/Common/Loading.fxml"));
        mainStage = stage;
        mainStage.setScene(new Scene(loadingView));
        mainStage.setTitle("Wczytywanie...");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


        mainStage.setX((primaryScreenBounds.getMaxX() + primaryScreenBounds.getMinX()) / 2 - 177);
        mainStage.setY((primaryScreenBounds.getMaxY() + primaryScreenBounds.getMinY()) / 2 - 108);

        mainStage.show();
    }

    public static Stage getStage() {
        return mainStage;
    }
}
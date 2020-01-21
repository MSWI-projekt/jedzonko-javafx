package jedzonko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jedzonko.controller.common.Loading;
import jedzonko.utils.DBManager;

public class MainApp extends Application
{
	@Override
	public void init()
	{
		DBManager.init();
	}

	@Override
	public void start(Stage mainStage) throws Exception
	{
		Parent welcomeView = FXMLLoader.load(getClass().getResource("/View/Common/Welcome.fxml"));
		mainStage = Loading.getStage();
		mainStage.setScene(new Scene(welcomeView));
		mainStage.setTitle("jedzonko");
		mainStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}

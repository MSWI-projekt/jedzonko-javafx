package jedzonko.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Controller
{
	public void initView(String arg1)
	{
		//Overridden and implemented by child classes
	}
	
	public void initView(String arg1, int arg2)
	{
		//Overridden and implemented by child classes
	}
	
	protected void changeScene(ActionEvent event, String viewName)
	{
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		viewName = "/View/" + viewName + ".fxml";
		
		Parent newView;
		try
		{
			newView = FXMLLoader.load(getClass().getResource(viewName));
			window.setScene(new Scene(newView));
			window.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			error("Nie udało się załadować widoku: " + viewName);
		}
	}
	
	protected void changeScene(ActionEvent event, String viewName, String parameter)
	{
		FXMLLoader loader = getLoader(viewName);
		try
		{
			Scene scene = new Scene(loader.load());
			Controller controller = loader.getController();
			controller.initView(parameter);
			changeScene(event, scene);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			error("Nie udało się załadować widoku: " + viewName);
		}
	}
	
	protected void changeScene(ActionEvent event, String viewName, String parameter1, int parameter2)
	{
		FXMLLoader loader = getLoader(viewName);
		try
		{
			Scene scene = new Scene(loader.load());
			Controller controller = loader.getController();
			controller.initView(parameter1, parameter2);
			changeScene(event, scene);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			error("Nie udało się załadować widoku: " + viewName);
		}
	}
	
	protected void error(String message)
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Błąd!");
		alert.setHeaderText(message);
		alert.setContentText(null);
		
		alert.showAndWait();
	}
	
	private FXMLLoader getLoader(String viewName)
	{
		viewName = "/View/" + viewName + ".fxml";
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		return loader;
	}
	
	private void changeScene(ActionEvent event, Scene scene)
	{
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}

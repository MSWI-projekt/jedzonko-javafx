package jedzonko.controller.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jedzonko.controller.common.Controller;

public class Main extends Controller
{
	@FXML private Label label;
	private String restaurant;
	
	@Override
	public void initView(String restaurant)
	{
		this.restaurant = restaurant;
		label.setText("Panel restauracji: " + restaurant);
	}
	
	public void changeSceneToRestaurantMenu(ActionEvent event)
	{
		changeScene(event, "Restaurant/Menu", restaurant);
	}
	
	public void changeSceneToRestaurantOrders(ActionEvent event)
	{
		changeScene(event, "Restaurant/Orders", restaurant);
	}
	
	public void changeSceneToWelcome(ActionEvent event)
	{
		changeScene(event, "Common/Welcome");
	}
}

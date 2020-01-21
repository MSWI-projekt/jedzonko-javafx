package jedzonko.controller.admin;

import javafx.event.ActionEvent;
import jedzonko.controller.common.Controller;

public class Main extends Controller
{
	public void changeSceneToAddRestaurant(ActionEvent event)
	{
		changeScene(event, "Admin/AddRestaurant");
	}
	
	public void changeSceneToWelcome(ActionEvent event)
	{
		changeScene(event, "Common/Welcome");
	}
}

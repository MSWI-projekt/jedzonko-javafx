package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import jedzonko.controller.common.Controller;

public class ThankYou extends Controller
{
	public void changeSceneToCustomerMain(ActionEvent event)
	{
		changeScene(event, "Customer/Main");
	}
}

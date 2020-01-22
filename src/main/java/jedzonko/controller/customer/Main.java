package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jedzonko.controller.common.Controller;
import jedzonko.model.Account;
import jedzonko.utils.DBManager;

import java.util.List;

public class Main extends Controller
{
	@FXML private ListView<String> listView;
	
	public void initialize()
	{
		restaurant = null;
		List<Account> restaurants = DBManager.selectAllWhere("Account", "type", "restaurant");
		restaurants.forEach(restaurant -> listView.getItems().add(restaurant.getLogin()));
	}
	
	public void changeSceneToWelcome(ActionEvent event)
	{
		changeScene(event, "Common/Welcome");
	}

	public void validateAndGoToMenu(ActionEvent event)
	{
		if (listView.getSelectionModel().getSelectedIndex() == -1)
		{
			error("Nie wybrano żadnej restauracji");
		}
		else
		{
			restaurant = listView.getSelectionModel().getSelectedItem();
			changeScene(event, "Customer/Menu");
		}
	}
}

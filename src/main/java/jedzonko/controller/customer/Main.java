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
		List<Account> restaurants = DBManager.selectAllWhere("Account", "type", "restaurant");
		restaurants.forEach(restaurant -> listView.getItems().add(restaurant.getLogin()));
	}
	
	public void changeSceneToWelcome(ActionEvent event)
	{
		changeScene(event, "Common/Welcome");
	}

	public void validateAndGoToMenu(ActionEvent event)
	{
		String restaurant = getSelectedRestaurant();
		if (restaurant != null)
		{
			changeScene(event, "Customer/Menu", restaurant);
		}
		else
		{
			error("Nie wybrano żadnej restauracji");
		}
	}

	private String getSelectedRestaurant()
	{
		if (listView.getSelectionModel().getSelectedIndex() == -1)
		{
			return null;
		}
		return listView.getSelectionModel().getSelectedItem();
	}
}

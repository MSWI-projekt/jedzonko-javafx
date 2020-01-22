package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jedzonko.controller.common.Controller;
import jedzonko.model.Dish;
import jedzonko.utils.DBManager;

import java.util.List;

public class Menu extends Controller
{
	@FXML private ListView<String> listView;
	
	public void initialize()
	{
		dish = null;
		List<Dish> dishes = DBManager.selectAllWhere("Dish", "restaurant", restaurant);
		dishes.forEach(dish -> listView.getItems().add(dish.toString()));
	}
	
	public void changeSceneToCustomerMain(ActionEvent event)
	{
		changeScene(event, "Customer/Main");
	}

	public void validateAndGoConfirmation(ActionEvent event)
	{
		if (listView.getSelectionModel().getSelectedIndex() != -1)
		{
			dish = listView.getSelectionModel().getSelectedItem();
			changeScene(event, "Customer/ConfirmOrder");
		}
		else
		{
			error("Nie wybrano żadnej restauracji");
		}
	}
}

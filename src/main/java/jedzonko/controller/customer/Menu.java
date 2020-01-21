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
	private String restaurant;
	
	public void changeSceneToCustomerMain(ActionEvent event)
	{
		changeScene(event, "Customer/Main");
	}
	
	public void initView(String restaurant)
	{
		this.restaurant = restaurant;
		List<Dish> dishes = DBManager.selectAllWhere("Dish", "restaurant", restaurant);
		dishes.forEach(dish -> listView.getItems().add(dish.toString()));
	}

	public void validateAndGoConfirmation(ActionEvent event)
	{
		int index = getSelectedIndex();
		if (index != -1)
		{
			changeScene(event, "Customer/ConfirmOrder", restaurant, index);
		}
		else
		{
			error("Nie wybrano żadnej restauracji");
		}
	}

	private int getSelectedIndex()
	{
		return listView.getSelectionModel().getSelectedIndex();
	}
}

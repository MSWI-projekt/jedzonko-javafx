package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jedzonko.controller.common.Controller;
import jedzonko.model.Dish;
import jedzonko.model.OrderDish;
import jedzonko.utils.DBManager;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Controller
{
	@FXML private ListView<String> listView;
	
	public void initialize()
	{
		if (orderDishes == null)
		{
			orderDishes = new ArrayList<>();
			List<Dish> dishes = DBManager.selectAllWhere("Dish", "restaurant", restaurant);
			dishes.forEach(dish -> orderDishes.add(new OrderDish(dish.getName(), dish.toString(), dish.getPrice())));
		}
		orderDishes.forEach(dish -> listView.getItems().add(dish.getLongString()));
	}
	
	public void changeSceneToCustomerMain(ActionEvent event)
	{
		changeScene(event, "Customer/Main");
	}

	public void validateAndGoConfirmation(ActionEvent event)
	{
		for (OrderDish orderDish : orderDishes)
		{
			if (orderDish.isOrdered())
			{
				changeScene(event, "Customer/ConfirmOrder");
				return;
			}
		}
		
		error("Nie wybrano żadnej potrawy");
	}
	
	public void incQuantity()
	{
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index == -1)
		{
			return;
		}
		
		orderDishes.get(index).incQuantity();
		listView.getItems().set(index, orderDishes.get(index).getLongString());
	}
	
	public void decQuantity()
	{
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index == -1)
		{
			return;
		}
		
		orderDishes.get(index).decQuantity();
		listView.getItems().set(index, orderDishes.get(index).getLongString());
	}
}

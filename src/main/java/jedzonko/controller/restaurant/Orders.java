package jedzonko.controller.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jedzonko.controller.common.Controller;
import jedzonko.model.Order;
import jedzonko.utils.DBManager;

import java.util.List;

public class Orders extends Controller
{
	private List<Order> orders;
	@FXML private ListView<String> listView;
	
	public void initialize()
	{
		orders = DBManager.selectAllWhere("Order", "restaurant", restaurant);
		for (Order order : orders)
		{
			if (order.isActive())
			{
				listView.getItems().add(order.toString());
			}
			else
			{
				orders.remove(order);
			}
		}
	}
	
	public void changeSceneToRestaurantMain(ActionEvent event)
	{
		changeScene(event, "Restaurant/Main");
	}
	
	public void deliverAll()
	{
		for (Order order : orders)
		{
			order.deliver();
			DBManager.update(order);
		}
		orders.clear();
		listView.getItems().clear();
	}
	
	public void deliverOne()
	{
		int selectedIndex = getIndex();
		if (selectedIndex != -1)
		{
			Order order = orders.get(selectedIndex);
			order.deliver();
			DBManager.update(order);
			listView.getItems().remove(selectedIndex);
			orders.remove(selectedIndex);
		}
		else
		{
			error("Nie wybrano żadnego zamówienia");
		}
	}
	
	private int getIndex()
	{
		return listView.getSelectionModel().getSelectedIndex();
	}
}

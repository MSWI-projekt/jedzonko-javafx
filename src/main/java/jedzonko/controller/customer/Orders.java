package jedzonko.controller.customer;

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
		orders = DBManager.selectAllWhere("Order", "customer", login);
		orders.forEach(order -> listView.getItems().add(order.getInfoCustomer()));
	}
	
	public void changeSceneToCustomerMain(ActionEvent event)
	{
		changeScene(event, "Customer/Main");
	}
}

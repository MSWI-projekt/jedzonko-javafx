package jedzonko.controller.restaurant;

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
	
	@Override
	public void initView(String restaurant)
	{
		this.restaurant = restaurant;
		List<Dish> dishes = DBManager.selectAllWhere("Dish", "restaurant", restaurant);
		for (Dish dish : dishes)
		{
			listView.getItems().add(dish.getName());
		}
	}
	
	public void changeSceneToRestaurantMain(ActionEvent event)
	{
		changeScene(event, "Restaurant/Main", restaurant);
	}
	
	public void changeSceneToRestaurantEdit(ActionEvent event)
	{
		changeScene(event, "Restaurant/EditDish", restaurant, -1);
	}
	
	public void validateAndGoToEditView(ActionEvent event)
	{
		if (getIndex() == -1)
		{
			error("Nie wybrano żadnej potrawy");
			return;
		}
		
		changeScene(event, "Restaurant/EditDish", restaurant, getIndex());
	}
	
	private int getIndex()
	{
		return listView.getSelectionModel().getSelectedIndex();
	}
}

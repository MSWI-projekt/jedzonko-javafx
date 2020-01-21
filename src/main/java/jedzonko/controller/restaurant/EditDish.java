package jedzonko.controller.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jedzonko.controller.common.Controller;
import jedzonko.model.Dish;
import jedzonko.utils.DBManager;
import jedzonko.utils.ValidateForm;

public class EditDish extends Controller
{
	@FXML private TextField name;
	@FXML private TextField price;
	@FXML private TextArea description;
	private Dish beforeEdit = null;
	private String restaurant;
	
	@Override
	public void initView(String restaurant, int selectedIndex)
	{
		this.restaurant = restaurant;
		if (selectedIndex != -1)
		{
			beforeEdit = DBManager.selectOneWhere("Dish", "restaurant", restaurant, selectedIndex);
			name.setText(beforeEdit.getName());
			price.setText(String.valueOf(beforeEdit.getPrice()));
			description.setText(beforeEdit.getDescription());
		}
	}
	
	public void changeSceneToRestaurantMenu(ActionEvent event)
	{
		changeScene(event, "Restaurant/Menu", restaurant);
	}
	
	public void delete(ActionEvent event)
	{
		if (beforeEdit != null)
		{
			DBManager.delete(beforeEdit);
		}
		changeSceneToRestaurantMenu(event);
	}
	
	public void validateAndSave(ActionEvent event)
	{
		String errorMessage = ValidateForm.validateDish(name.getText(), description.getText(), price.getText());
		if (!errorMessage.isEmpty())
		{
			error(errorMessage);
			return;
		}
		
		try
		{
			save(event);
		}
		catch (Exception e)
		{
			error("Nazwa potrawy jest już zajęta");
		}
	}
	
	private void save(ActionEvent event)
	{
		if (beforeEdit == null || !beforeEdit.getName().equals(name.getText()))
		{
			DBManager.insert(readForm());
			delete(event);
		}
		else
		{
			DBManager.update(readForm());
			changeSceneToRestaurantMenu(event);
		}
	}
	
	private Dish readForm()
	{
		return new Dish(name.getText(), description.getText(), Double.parseDouble(price.getText()), restaurant);
	}
}

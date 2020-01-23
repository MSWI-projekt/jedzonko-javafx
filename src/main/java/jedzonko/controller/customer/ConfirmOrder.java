package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jedzonko.controller.common.Controller;
import jedzonko.model.Account;
import jedzonko.model.Order;
import jedzonko.model.OrderDish;
import jedzonko.utils.DBManager;
import jedzonko.utils.ValidateForm;

public class ConfirmOrder extends Controller
{
	@FXML private TextArea order;
	@FXML private TextField name;
	@FXML private TextField address;
	@FXML private TextField phone;
	
	public void initialize()
	{
		setOrderText();
		Account account = (Account) DBManager.selectAllWhere("Account", "login", login).get(0);
		name.setText(account.getFullName());
		address.setText(account.getAddress());
		phone.setText(account.getPhoneNumber());
	}
	
	public void changeSceneToCustomerMenu(ActionEvent event)
	{
		changeScene(event, "Customer/Menu");
	}
	
	private void changeSceneToCustomerThankYou(ActionEvent event)
	{
		changeScene(event, "Customer/ThankYou");
	}
	
	public void validate(ActionEvent event)
	{
		String errorMessage = ValidateForm.validateOrder(name.getText(), address.getText(), phone.getText());
		if (errorMessage.isEmpty())
		{
			placeOrder(event);
		}
		else
		{
			error(errorMessage);
		}
	}
	
	private void placeOrder(ActionEvent event)
	{
		String orderText = order.getText().replaceAll("\n", " ").trim();
		Order newOrder = new Order(orderText, name.getText(), address.getText(), phone.getText(), restaurant);
		DBManager.insert(newOrder);
		changeSceneToCustomerThankYou(event);
	}
	
	private void setOrderText()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (OrderDish orderDish : orderDishes)
		{
			if (orderDish.isOrdered())
			{
				stringBuilder.append(orderDish.getShortString());
				stringBuilder.append("\n");
			}
		}
		order.setText(stringBuilder.toString());
	}
}

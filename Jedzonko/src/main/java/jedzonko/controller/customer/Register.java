package jedzonko.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jedzonko.controller.common.Controller;
import jedzonko.model.Account;
import jedzonko.utils.DBManager;

public class Register extends Controller
{
	@FXML private TextField login;
	@FXML private PasswordField password;
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField email;
	@FXML private TextField phoneNumber;
	@FXML private TextField street;
	@FXML private TextField postalCode;
	@FXML private TextField city;
	@FXML private TextField houseNumber;
	
	public void register(ActionEvent event)
	{
		Account account = new Account(
				login.getText(),
				password.getText(),
				name.getText(),
				surname.getText(),
				postalCode.getText(),
				city.getText(),
				houseNumber.getText(),
				street.getText(),
				phoneNumber.getText(),
				email.getText()
		);
		
		DBManager.insert(account);
		changeSceneToWelcome(event);
	}
	
	public void changeSceneToWelcome(ActionEvent event)
	{
		changeScene(event, "Common/Welcome");
	}
}

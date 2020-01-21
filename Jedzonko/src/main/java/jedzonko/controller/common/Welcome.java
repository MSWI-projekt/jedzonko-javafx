package jedzonko.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jedzonko.utils.Login;

public class Welcome extends Controller
{
	@FXML private TextField login;
	@FXML private PasswordField password;
	
	public void login(ActionEvent event)
	{
		String type = Login.validateCredentials(login.getText(), password.getText());
		
		switch (type)
		{
			case "admin":
				changeScene(event, "Admin/Main");
				break;
			case "customer":
				changeScene(event, "Customer/Main");
				break;
			case "restaurant":
				changeScene(event, "Restaurant/Main", login.getText());
				break;
			case "wrong login":
				error("Niepoprawny login");
				break;
			case "wrong password":
				error("Niepoprawne hasło");
				break;
		}
	}
	
	public void changeSceneToRegister(ActionEvent event)
	{
		changeScene(event, "Customer/Register");
	}
}

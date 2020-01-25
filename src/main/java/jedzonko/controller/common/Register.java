package jedzonko.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jedzonko.model.Account;
import jedzonko.utils.DBManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends Controller {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField street;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private TextField houseNumber;

    private Stage errorWindows;

    public void register(ActionEvent event) {
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

        String error = "";

        if (errorWindows==null){
            errorWindows = new Stage();
        }

        if (account.getLogin().equals("")) {
            error = "\nNie podano loginu.\n";
        }
        if (account.getPassword() == 0) {
            error += "\nNie podano hasła.\n";
        }
        if (account.getName().equals("")) {
            error += "\nNie podano imienia.\n";
        }
        if (account.getSurname().equals("")) {
            error += "\nNie podano nazwiska.\n";
        }
        if (account.getCity().equals("")) {
            error += "\nNie podano miasta.\n";
        }
        if (!isCorrectHouseNumber(account.getHouseNumber())) {
            error += "\nNumer domu nie jest poprawny.\n";
        }
        if (!isCorrectPostalCode(account.getPostcode())) {
            error += "\nNumer kodu pocztowego nie jest poprawny.\n";
        }
        if (!isCorrectEmail(account.getEmail())) {
            error += "\nEmail nie jest poprawny.\n";
        }
        if (!isCorrectNumberPhone(account.getPhoneNumber())) {
            error += "\nNumer telefonu nie jest poprawny.\n";
        }
        if (account.getPassword()==0) {
            error += "\nHasło nie jest poprawne.\n";
        }

        if (!isCorrectAdress(account.getAddress()) ){
            error += "\nAdres nie jest poprawny.\n";
        }



        if (error.equals("")) {
            try {
                DBManager.insert(account);
                changeSceneToWelcome(event);
            } catch (Exception e) {
                error = "Podany login lub telefon są już w bazie danych";
                errorView(error);

            }

        } else {
            System.out.println("Error");
            errorView(error);
        }


    }

    public boolean isCorrectAdress(String adress) {

        return adress.length() >= 3;

    }


    public boolean isCorrectNumberPhone(String number) {
        try {
            int numberInteger = Integer.parseInt(number);
            if (number.length() == 9) throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e) {

            return false;
        }

    }


    public void errorView(String error) {
        Label secondLabel = new Label(error);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 300, 400);



        errorWindows.setTitle("Wystąpiły błędy!!!");
        errorWindows.setScene(secondScene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


        errorWindows.setX((primaryScreenBounds.getMaxX() + primaryScreenBounds.getMinX()) / 2 - 200);
        errorWindows.setY((primaryScreenBounds.getMaxY() + primaryScreenBounds.getMinY()) / 2 - 150);

        errorWindows.show();
    }

    public boolean isCorrectEmail(String email) {

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();


    }

    public boolean isCorrectPostalCode(String postalCode) {
        try {
            int houseNumberInteger = Integer.parseInt(postalCode);
            if (!(postalCode.length() == 5)) {
                throw new NumberFormatException();
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public boolean isCorrectHouseNumber(String houseNumber) {
        try {
            int houseNumberInteger = Integer.parseInt(houseNumber);
            if (houseNumberInteger < 0 || houseNumberInteger > 9999) {
                throw new NumberFormatException();
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void changeSceneToWelcome(ActionEvent event) {
        changeScene(event, "Common/Welcome");
    }




}

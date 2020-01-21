package jedzonko;

import javafx.application.Application;
import jedzonko.controller.common.Loading;

public class Preloader
{
    public static void main(String[] args)
    {
        System.setProperty("javafx.preloader", Loading.class.getName());
        Application.launch(MainApp.class, args);
    }
}
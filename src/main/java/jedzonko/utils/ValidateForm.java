package jedzonko.utils;

public class ValidateForm
{
	public static String validateDish(String name, String toppings, String price)
	{
		if (name.isEmpty() || toppings.isEmpty() || price.isEmpty())
		{
			return "Prosimy wypełnić wszystkie pola";
		}
		try
		{
			Double.parseDouble(price);
			return "";
		}
		catch (NumberFormatException e)
		{
			return "Wprowadzona cena nie jest liczbą";
		}
	}
	
	public static String validateOrder(String name, String address, String phone)
	{
		if (name.isEmpty() || address.isEmpty() || phone.isEmpty())
		{
			return "Prosimy wypełnić wszystkie pola";
		}
		return "";
	}
}

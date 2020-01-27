package jedzonko.model;

public class OrderDish
{
	private String dishShortName;
	private String dishLongName;
	private int quantity;
	private double price;
	
	public OrderDish (String dishShortName, String dishLongName, double price)
	{
		quantity = 0;
		this.dishShortName = dishShortName;
		this.dishLongName = dishLongName;
		this.price = price;
	}
	
	public void incQuantity()
	{
		quantity++;
	}
	
	public void decQuantity()
	{
		if (quantity > 0)
		{
			quantity--;
		}
	}
	
	public boolean isOrdered()
	{
		return quantity > 0;
	}
	
	public double getPrice()
	{
		return quantity * price;
	}
	
	public String getShortString()
	{
		return dishShortName + " x" + quantity + " " + String.format("%.2f zł", getPrice());
	}
	
	public String getLongString()
	{
		return dishLongName + " Ilość: " + quantity;
	}
}

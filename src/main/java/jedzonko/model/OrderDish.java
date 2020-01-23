package jedzonko.model;

public class OrderDish
{
	private String dishShortName;
	private String dishLongName;
	private int quantity;
	
	public OrderDish (String dishShortName, String dishLongName)
	{
		quantity = 0;
		this.dishShortName = dishShortName;
		this.dishLongName = dishLongName;
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
	
	public String getShortString()
	{
		return dishShortName + " x" + quantity;
	}
	
	public String getLongString()
	{
		return dishLongName + " Ilość: " + quantity;
	}
}
